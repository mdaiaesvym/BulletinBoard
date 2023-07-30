package mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.example.demo.BulletinBoardApplication;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;

@MybatisTest
@ContextConfiguration(classes = BulletinBoardApplication.class)
public class BulletinBoardTest {

  @Autowired
  private BulletinBoardMapper mapper;

  @Test
  public void スレッド追加_成功() {
    Thread thread = new Thread();
    thread.setThreadName("テスト");

    assertTrue(mapper.insertThread(thread));
  }

  @Test
  public void スレッド一覧取得() {
    List<Thread> threadList = mapper.selectThreadList();
    assertThat(threadList.get(0).getThreadNumber()).isEqualTo(2);
    assertThat(threadList.get(0).getThreadName()).isEqualTo("今年のベストゲームソフト");
    assertThat(threadList.get(0).getUpdatedAt()).isEqualTo("2022-01-01T10:25:10.000");
    assertThat(threadList.get(0).getMessageCount()).isEqualTo(3);
  }

  @Test
  public void メッセージ追加_投稿者名あり_成功() {
    Message message = new Message();
    message.setThreadNumber(1);
    message.setContributorName("テスト 投稿者");
    message.setMessage("テストメッセージ");

    assertTrue(mapper.insertMessage(message));
  }

  @Test
  public void メッセージ追加_投稿者名なし_成功() {
    Message message = new Message();
    message.setThreadNumber(1);
    message.setMessage("テストメッセージ");

    assertTrue(mapper.insertMessage(message));
  }

  @Test
  public void オートインクリメント取得() {
    Integer threadMaxNumber = mapper.selectThreadMaxNumber();

    assertThat(threadMaxNumber).isEqualTo(3);
  }

  @Test
  public void メッセージ一覧取得() {
    List<Message> messageList = mapper.selectMessageList(1);

    assertThat(messageList.get(0).getThreadNumber()).isEqualTo(1);
    assertThat(messageList.get(0).getMessage()).isEqualTo("今何をしていますか？");
    assertThat(messageList.get(0).getUpdatedAt()).isEqualTo("2022-01-01T10:10:10.000");
    assertThat(messageList.get(0).getContributorName()).isEqualTo("匿名");
  }

  @Test
  public void スレッド名取得() {
    String threadName = mapper.selectThreadName(1);

    assertThat(threadName).isEqualTo("雑談");
  }

  @Test
  public void スレッド数取得() {
    Integer threadCount = mapper.selectThreadCount();

    assertThat(threadCount).isEqualTo(3);
  }
}
