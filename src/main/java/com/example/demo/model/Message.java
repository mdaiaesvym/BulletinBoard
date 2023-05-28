package com.example.demo.model;

import lombok.Data;

@Data
public class Message {

  // スレッド番号
  private Integer threadNumber;
  // メッセージ
  private String message;
  // 投稿者名
  private String contributorName;
}
