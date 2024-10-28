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
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.ThreadService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BulletinBoardApplication.class)
public class ThreadServiceTest {

  @Autowired
  private ThreadService threadService;

  @MockBean
  private BulletinBoardMapper mapper;

  @Test
  public void makeThread_マッパー呼び出し確認_引数が正しい() {
    // データ作成
    Thread thread = new Thread();
    thread.setThreadName("テスト_スレッド名");

    // ArgumentCaptorの作成
    ArgumentCaptor<Thread> argumentCaptor = ArgumentCaptor.forClass(Thread.class);

    // 実行
    threadService.makeThread(thread);

    // ArgumentCaptorを使用して引数をキャプチャ
    verify(mapper).makeThread(argumentCaptor.capture());

    // 呼び出されたことを確認
    verify(mapper, times(1)).makeThread(thread);

    // 引数確認
    assertEquals(null, argumentCaptor.getValue().getThreadNumber());
    assertEquals("テスト_スレッド名", argumentCaptor.getValue().getThreadName());
    assertEquals(null, argumentCaptor.getValue().getMessageCount());
    assertEquals(null, argumentCaptor.getValue().getUpdatedYmdhms());
  }

  @Test
  public void getThreadList_マッパー呼び出し確認() {

    // 実行
    threadService.getThreadList();

    // 呼び出されたことを確認
    verify(mapper, times(1)).getThreadList();
  }
}
