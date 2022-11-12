package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class MakeThreadForm {

  @NotBlank
  @Length(min = 1, max = 100)
  private String threadName;

  @NotBlank
  @Length(min = 1, max = 1000)
  private String message;

  private Integer checkContributorName = 0;

  @NotBlank
  @Length(min = 1, max = 100)
  private String contributorName;
}
