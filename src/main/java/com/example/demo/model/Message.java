package com.example.demo.model;

import java.util.Date;
import lombok.Data;

@Data
public class Message {

  // スレッド番号
  private Integer threadNumber;
  // メッセージ
  private String message;
  // 更新日時
  private Date updatedAt;
  // 投稿者名
  private String contributorName;
}
