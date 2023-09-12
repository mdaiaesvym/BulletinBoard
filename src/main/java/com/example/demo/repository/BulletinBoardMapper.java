package com.example.demo.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

@Mapper
public interface BulletinBoardMapper {

  /** スレッド追加 */
  public Integer insertThread(Thread thread);

  /** スレッドナンバー・スレッド名・メッセージ数取得 */
  public List<Thread> getThreadsInfo();

  /** メッセージ追加 */
  public boolean addMessage(Message message);

  /** メッセージ一覧取得 */
  public List<Message> getMessageas(Integer threadNumber);

  /** スレッド名取得 */
  public String getThreadName(Integer threadNumber);

  /** スレッド数取得 */
  public Integer getThreadCount();
}
