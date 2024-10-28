package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class Message {

  // スレッド番号
  private Integer threadNumber;
  // メッセージ
  private String message;
  // 作成日時（フォーマット：yyyyMMddhhmmss）
  private String createdYmdhms;
  // 投稿者名
  private String contributorName;

  // yyyyMMddhhmmss→yyyy/MM/dd hh:mm:ssに変換
  public String getFormatedCreatedYmdhms() {
    // 入力フォーマットと出力フォーマットを定義
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    // 文字列をLocalDateTimeに変換してから、出力形式にフォーマット
    LocalDateTime dateTime = LocalDateTime.parse(this.createdYmdhms, inputFormatter);
    return dateTime.format(outputFormatter);
  }
}
