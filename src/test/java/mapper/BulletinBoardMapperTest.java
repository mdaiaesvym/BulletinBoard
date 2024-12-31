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
public class BulletinBoardMapperTest {

  @Autowired
  private BulletinBoardMapper mapper;

  @Test
  public void スレッド追加_成功() {
    Thread thread = new Thread();
    thread.setThreadName("テスト");

    assertThat(mapper.makeThread(thread)).isEqualTo(1);
    assertThat(thread.getThreadNumber()).isEqualTo(4);
  }

  @Test
  public void スレッド一覧取得() {
    List<Thread> threadList = mapper.getThreadList();
    assertThat(threadList.get(0).getThreadNumber()).isEqualTo(2);
    assertThat(threadList.get(0).getThreadName()).isEqualTo("今年のベストゲームソフト");
    assertThat(threadList.get(0).getUpdatedYmdhms()).isEqualTo("20220101102010");
    assertThat(threadList.get(0).getMessageCount()).isEqualTo(3);
  }

  @Test
  public void メッセージ追加_投稿者名あり_成功() {
    Message message = new Message();
    message.setThreadNumber(1);
    message.setContributorName("テスト 投稿者");
    message.setMessage("テストメッセージ");

    assertTrue(mapper.addMessage(message));
  }

  @Test
  public void メッセージ追加_投稿者名なし_成功() {
    Message message = new Message();
    message.setThreadNumber(1);
    message.setMessage("テストメッセージ");

    assertTrue(mapper.addMessage(message));
  }

  @Test
  public void メッセージ一覧取得() {
    List<Message> messageList = mapper.getMessageList(1);

    assertThat(messageList.get(0).getThreadNumber()).isEqualTo(1);
    assertThat(messageList.get(0).getMessage()).isEqualTo("今何をしていますか？");
    assertThat(messageList.get(0).getCreatedYmdhms()).isEqualTo("20220101101010");
    assertThat(messageList.get(0).getContributorName()).isEqualTo("山田一郎");
  }

  @Test
  public void スレッド名取得() {
    String threadName = mapper.getThreadName(1);

    assertThat(threadName).isEqualTo("雑談");
  }

  @Test
  public void スレッド更新_成功() {
    Message message = new Message();
    message.setThreadNumber(1);
    message.setContributorName("テスト 投稿者");

    assertTrue(mapper.updateThread(message));
  }
}
