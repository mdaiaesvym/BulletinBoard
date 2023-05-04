package com.example.demo.controller.originValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = ContributorNameValidation.class)
public @interface ConfirmContributorName {

  // 独自メッセージ設定
  String message() default "{msg.confirmContributorName}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String hasContributorName();

  String contributorName();

  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    ConfirmContributorName[] values();
  }
}
