package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.BulletinBoardApplication;

@SpringBootTest(classes = BulletinBoardApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MakeThreadControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private final String ENDPOINT_THREAD = "/makeThread";
  private final String MAKE_THREAD = "makeThread";

  @Test
  public void アクセス成功() throws Exception {
    // アクセス
    mockMvc.perform(get(ENDPOINT_THREAD))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成成功_投稿者名フラグオフ() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "メッセージテスト")
        .param("hasContributorName", "false").param("contributorName", ""))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("threads"));
  }

  @Test
  public void 新規作成成功_投稿者名フラグオン() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "メッセージテスト")
        .param("hasContributorName", "true").param("contributorName", "投稿者テスト"))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("threads"));
  }

  @Test
  public void 新規作成失敗_スレッド名が空() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "").param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_メッセージが空() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_投稿者フラグがオンで投稿者名が空() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "メッセージテスト")
        .param("hasContributorName", "true").param("contributorName", ""))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }


  @Test
  public void 新規作成失敗_スレッド名が半角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", " ").param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_メッセージが半角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", " ").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_投稿者フラグがオンで投稿者名が半角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "メッセージテスト")
        .param("hasContributorName", "true").param("contributorName", " "))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_スレッド名が全角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "　").param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_メッセージが全角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "　").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }

  @Test
  public void 新規作成失敗_投稿者フラグがオンで投稿者名が全角スペース() throws Exception {
    // ポスト
    mockMvc.perform(post(ENDPOINT_THREAD)
        // params = "makeThread"の呼び出し
        .param(MAKE_THREAD, "")
        // formに値を設定
        .param("threadName", "スレッド名テスト").param("message", "メッセージテスト")
        .param("hasContributorName", "true").param("contributorName", "　"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name(MAKE_THREAD));
  }
}
