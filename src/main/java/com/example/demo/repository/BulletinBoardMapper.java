package com.example.demo.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

@Mapper
public interface BulletinBoardMapper {

  /** スレッド追加 */
  public boolean insertThread(Thread thread);

  /** スレッドナンバー・スレッド名・メッセージ数取得 */
  public List<Thread> selectThreadList();

  /** メッセージ追加 */
  public boolean insertMessage(Message message);

  /** オートインクリメント取得 */
  public Integer threadMaxNumber();

  /** メッセージ一覧取得 */
  public List<Message> selectMessageList(String threadNumber);

  /** スレッド名取得 */
  public String selectThreadName(String threadNumber);

  /** スレッド数取得 */
  public Integer selectThreadCount();
}
