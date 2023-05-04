package com.example.demo.controller.originValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ContributorNameValidation
    implements ConstraintValidator<ConfirmContributorName, Object> {

  private String message;
  private String hasContributorName;
  private String contributorName;

  /**
   * 初期化処理
   * 
   */
  @Override
  public void initialize(ConfirmContributorName annotation) {
    this.message = annotation.message();
    this.hasContributorName = annotation.hasContributorName();
    this.contributorName = annotation.contributorName();
  }

  /**
   * バリデーション処理
   * 
   */
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);

    // 投稿者名フラグ取得
    boolean hasContributorName = (boolean) beanWrapper.getPropertyValue(this.hasContributorName);
    // 投稿者名取得
    String contributorName = (String) beanWrapper.getPropertyValue(this.contributorName);

    // バリデーションエラー
    // ・投稿者名フラグオン
    // ・投稿者名が1未満、100超過
    if (hasContributorName && contributorName.length() < 1 || contributorName.length() > 100) {

      context.buildConstraintViolationWithTemplate(message).addPropertyNode("contributorName")
          .addConstraintViolation();

      return false;
    }

    return true;
  }
}
