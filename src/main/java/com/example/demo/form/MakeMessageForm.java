package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import com.example.demo.controller.originAnnotation.ConfirmContributorName;
import lombok.Data;

@Data
@ConfirmContributorName(hasContributorName = "hasContributorName",
    contributorName = "contributorName")
public class MakeMessageForm {

  // スレッド番号
  private Integer threadNumber;

  // メッセージ
  @NotBlank
  @Length(max = 1000)
  private String message;

  // 投稿者名フラグ
  private boolean hasContributorName = false;

  // 投稿者名
  @Length(max = 100)
  private String contributorName;
}
