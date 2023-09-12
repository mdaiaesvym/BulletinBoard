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
  public List<Thread> selectThreadList();

  /** メッセージ追加 */
  public boolean insertMessage(Message message);

  /** メッセージ一覧取得 */
  public List<Message> selectMessageList(Integer threadNumber);

  /** スレッド名取得 */
  public String selectThreadName(Integer threadNumber);

  /** スレッド数取得 */
  public Integer selectThreadCount();
}
