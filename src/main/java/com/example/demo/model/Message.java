package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Message {

  // スレッド番号
  private Integer threadNumber;
  // メッセージ
  private String message;
  // 更新日時（フォーマット：yyyyMMddhhmmss）
  private Date updatedAt;
  // 投稿者名
  private String contributorName;

  // yyyyMMddhhmmss→yyyy/MM/dd hh:mm:ssに変換
  public String getFormatUpdatedAt() {
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return outputFormat.format(this.updatedAt);
  }
}
