package com.example.demo.model;

import lombok.Data;

@Data
public class Thread {

  // スレッド番号
  private String threadNumber;
  // スレッド名
  private String threadName;
  // コメント数
  private Integer messageCount;
}
