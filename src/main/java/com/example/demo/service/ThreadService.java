package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Thread;

public interface ThreadService {

  /**
   * スレッド追加
   * 
   * @param thread
   */
  public boolean makeThread(Thread thread);

  /**
   * スレッド名・スレッドごとのメッセージ数取得
   * 
   * @return
   */
  public List<Thread> getThreadNameCount();

  /**
   * スレッド数の最大値を取得
   * 
   * @return
   */
  public int getThreadMaxNumber();

}
