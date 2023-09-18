package controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.BulletinBoardApplication;
import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

@SpringBootTest(classes = BulletinBoardApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessagesControllerTest {

  private final String NOTFOUND = "notFound";
  private final String MESSAGES = "messages";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  MessageService messageService;

  // テスト実行時にh2データベースは立ち上がるが、
  // なぜかControllerのテストだけ初期データ（data.sql）が設定されない
  @BeforeEach
  public void setUp() throws ParseException {
    // メッセージ追加に成功すること
    when(messageService.addMessage(any())).thenReturn(true);

    // スレッド番号１のメッセージを設定
    List<Message> messageList = new ArrayList<>();
    Message message = new Message();
    message.setThreadNumber(1);
    message.setMessage("テスト");
    message.setContributorName("テスト投稿者");
    String strDate = "20230918101010";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    message.setUpdatedAt(dateFormat.parse(strDate));
    messageList.add(message);

    when(messageService.getMessageList(1)).thenReturn(messageList);
  }

  @Test
  public void 存在するページにアクセス() throws Exception {
    mockMvc.perform(get("/messages?threadNumber=1"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name(MESSAGES))
        // modelに存在することのテスト
        .andExpect(model().attribute("messageList", messageService.getMessageList(1)))
        .andExpect(model().attribute("threadName", messageService.getThreadName(1)))
        .andExpect(model().attribute("threadNumber", 1));
  }

  @Test
  public void 存在しないページにアクセス() throws Exception {
    mockMvc.perform(get("/messages?threadNumber=2"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name(NOTFOUND));

    mockMvc.perform(get("/messages?threadNumber=0"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name(NOTFOUND));
  }

  @Test
  public void メッセージ投稿成功_投稿者名フラグオフ() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "false")
        .param("contributorName", ""))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("messages?threadNumber=1"));
  }

  @Test
  public void メッセージ投稿成功_投稿者名フラグオン() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("messages?threadNumber=1"));
  }

  @Test
  public void メッセージ投稿失敗_メッセージが空() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MESSAGES));
  }

  @Test
  public void メッセージ投稿失敗_投稿者が空() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", ""))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MESSAGES));
  }

  @Test
  public void メッセージ投稿失敗_投稿者フラグがオンで投稿者名が空() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", ""))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MESSAGES));
  }

}
