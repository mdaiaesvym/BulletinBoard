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
   * スレッド情報取得
   * 
   * @return
   */
  public List<Thread> getThreadList();

}
