package com.example.demo.form;

import org.hibernate.validator.constraints.Length;
import com.example.demo.controller.originValidation.ConfirmContributorName;
import lombok.Data;

@Data
@ConfirmContributorName(hasContributorName = "hasContributorName",
    contributorName = "contributorName")
public class MakeMessageForm {

  // スレッド番号
  private String threadNumber;

  // メッセージ
  @Length(min = 1, max = 1000)
  private String message;

  // 投稿者名フラグ
  private boolean hasContributorName = false;

  // 投稿者名
  private String contributorName;
}
