package com.example.demo.form;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class MakeThreadForm {

  // スレッド名
  @Length(min = 1, max = 100)
  private String threadName;

  // メッセージ
  @Length(min = 1, max = 1000)
  private String message;

  // 投稿者名フラグ
  private boolean hasContributorName = false;

  // 投稿者名
  @Length(min = 1, max = 100)
  private String contributorName;
}
