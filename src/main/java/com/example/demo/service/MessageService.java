package com.example.demo.service;

import java.util.List;
import com.example.demo.form.MakeMessageForm;
import com.example.demo.model.Message;

public interface MessageService {

  /** スレッド数取得 */
  public Integer getThreadCount();

  /**
   * メッセージ一覧取得
   * 
   * @param threadNumber
   * @return
   */
  public List<Message> getMessageas(String threadNumber);

  /**
   * スレッド名取得
   * 
   * @param threadNumber
   * @return
   */
  public String getThreadName(String threadNumber);

  /**
   * 投稿者名に匿名を設定
   * 
   * @param makeMessageForm
   */
  public void setContributorName(MakeMessageForm makeMessageForm);
}
