package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Thread;

public interface ThreadService {

  /**
   * スレッド追加
   * 
   * @param thread
   */
  public Integer makeThread(Thread thread);

  /**
   * スレッド名・スレッドごとのメッセージ数取得
   * 
   * @return
   */
  public List<Thread> getThreadNameCount();

}
