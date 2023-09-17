package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Thread {

  // スレッド番号
  private Integer threadNumber;
  // スレッド名
  private String threadName;
  // 更新日時（フォーマット：yyyyMMddhhmmss）
  private Date updatedAt;
  // コメント数
  private Integer messageCount;

  // yyyyMMddhhmmss→yyyy/MM/dd hh:mm:ssに変換
  public String getFormatUpdatedAt() {
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return outputFormat.format(this.updatedAt);
  }
}
