package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.BulletinBoardApplication;
import com.example.demo.model.Message;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BulletinBoardApplication.class)
public class MessageServiceTest {

  @Autowired
  private MessageService messageService;

  @MockBean
  private BulletinBoardMapper mapper;

  @Test
  public void addMessage_マッパー呼び出し確認_引数が正しい() {
    // データ作成
    Message message = new Message();
    message.setThreadNumber(1);
    message.setContributorName("テスト投稿者");
    message.setMessage("テストメッセージ");

    // ArgumentCaptorの作成
    ArgumentCaptor<Message> argumentAddMessage = ArgumentCaptor.forClass(Message.class);
    ArgumentCaptor<Message> argumentUpdateThread = ArgumentCaptor.forClass(Message.class);

    // 実行
    messageService.addMessage(message);

    // ArgumentCaptorを使用して引数をキャプチャ
    verify(mapper).addMessage(argumentAddMessage.capture());
    verify(mapper).updateThread(argumentUpdateThread.capture());

    // 呼び出されたことを確認
    verify(mapper, times(1)).addMessage(message);

    // 引数確認
    assertEquals(1, argumentAddMessage.getValue().getThreadNumber());
    assertEquals("テスト投稿者", argumentAddMessage.getValue().getContributorName());
    assertEquals("テストメッセージ", argumentAddMessage.getValue().getMessage());
    assertEquals(null, argumentAddMessage.getValue().getCreatedYmdhms());
    assertEquals(1, argumentUpdateThread.getValue().getThreadNumber());
    assertEquals("テスト投稿者", argumentUpdateThread.getValue().getContributorName());
    assertEquals("テストメッセージ", argumentUpdateThread.getValue().getMessage());
    assertEquals(null, argumentUpdateThread.getValue().getCreatedYmdhms());
  }

  @Test
  public void getMessageList_マッパー呼び出し確認_引数が正しい() {
    // ArgumentCaptorの作成
    ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

    // 実行
    messageService.getMessageList(111);

    // ArgumentCaptorを使用して引数をキャプチャ
    verify(mapper).getMessageList(argumentCaptor.capture());

    // 呼び出されたことを確認
    verify(mapper, times(1)).getMessageList(111);

    // 引数確認
    assertEquals(111, argumentCaptor.getValue());
  }

  @Test
  public void getThreadName_マッパー呼び出し確認_引数が正しい() {
    // ArgumentCaptorの作成
    ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

    // 実行
    messageService.getThreadName(111);

    // ArgumentCaptorを使用して引数をキャプチャ
    verify(mapper).getThreadName(argumentCaptor.capture());

    // 呼び出されたことを確認
    verify(mapper, times(1)).getThreadName(111);

    // 引数確認
    assertEquals(111, argumentCaptor.getValue());
  }
}
