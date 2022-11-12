package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class MakeMessageForm {

  // ページ番号
  private String threadNumber;

  @NotBlank
  @Length(min = 1, max = 1000)
  // メッセージ
  private String message;

  // 投稿者名フラグ
  private Integer isContributorName = 0;

  @NotBlank
  @Length(min = 1, max = 100)
  // 投稿者名
  private String contributorName;
}
