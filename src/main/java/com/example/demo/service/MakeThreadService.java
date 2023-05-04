package com.example.demo.service;

import java.util.List;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

public interface MakeThreadService {

  /**
   * スレッド追加
   * 
   * @param thread
   */
  public void makeThread(Thread thread);

  /**
   * スレッド名・スレッドごとのメッセージ数取得
   * 
   * @return
   */
  public List<Thread> getThreadNameCount();

  /**
   * メッセージ追加
   * 
   * @param message
   */
  public void addMessage(Message message);

  /**
   * 投稿者名に匿名を設定
   * 
   * @param makethreadForm
   */
  public void setContributorName(MakeThreadForm makethreadForm);

  /**
   * スレッド数の最大値を取得
   * 
   * @return
   */
  public int getThreadMaxNumber();

}
