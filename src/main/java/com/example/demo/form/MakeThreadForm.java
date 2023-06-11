package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import com.example.demo.controller.originalAnnotation.ConfirmContributorName;
import lombok.Data;

@Data
@ConfirmContributorName(hasContributorName = "hasContributorName",
    contributorName = "contributorName")
public class MakeThreadForm {

  // スレッド名
  @NotBlank
  @Length(max = 100)
  private String threadName;

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
