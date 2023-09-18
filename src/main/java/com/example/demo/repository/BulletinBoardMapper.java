package com.example.demo.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

@Mapper
public interface BulletinBoardMapper {

  /** スレッド追加 */
  public Integer makeThread(Thread thread);

  /** スレッドナンバー・スレッド名・メッセージ数取得 */
  public List<Thread> getThreadList();

  /** メッセージ追加 */
  public boolean addMessage(Message message);

  /** メッセージ一覧取得 */
  public List<Message> getMessageList(Integer threadNumber);

  /** スレッド名取得 */
  public String getThreadName(Integer threadNumber);

}
