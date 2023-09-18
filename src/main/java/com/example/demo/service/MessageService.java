package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Message;

public interface MessageService {

  /**
   * メッセージ追加
   * 
   * @param message
   */
  public boolean addMessage(Message message);

  /**
   * メッセージ一覧取得
   * 
   * @param threadNumber
   * @return
   */
  public List<Message> getMessageList(Integer threadNumber);

  /**
   * スレッド名取得
   * 
   * @param threadNumber
   * @return
   */
  public String getThreadName(Integer threadNumber);
}
