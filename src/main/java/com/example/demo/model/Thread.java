package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class Thread {

  // スレッド番号
  private Integer threadNumber;
  // スレッド名
  private String threadName;
  // 更新日時（フォーマット：yyyyMMddhhmmss）
  private String updatedYmdhms;
  // コメント数
  private Integer messageCount;

  // yyyyMMddhhmmss→yyyy/MM/dd hh:mm:ssに変換
  public String getFormatedUpdatedYmdhms() {
    // 入力フォーマットと出力フォーマットを定義
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // 文字列をLocalDateTimeに変換してから、出力形式にフォーマット
    LocalDateTime dateTime = LocalDateTime.parse(this.updatedYmdhms, inputFormatter);
    return dateTime.format(outputFormatter);
  }
}
