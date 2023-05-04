package com.example.demo.service;

import java.util.List;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

public interface MakeThreadService {

  /** スレッド追加 */
  public void makeThread(Thread thread);

  /** スレッド名・スレッドごとのメッセージ数取得 */
  public List<Thread> getThreadNameCount();

  /** メッセージ追加 */
  public void addMessage(Message message);

  /** 投稿者名に匿名を設定 */
  public void setContributorName(MakeThreadForm makethreadForm);

  /** スレッド数の最大値を取得 */
  public int getThreadMaxNumber();

}
