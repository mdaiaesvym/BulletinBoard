package com.example.demo.controller.originAnnotation;

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

    // 投稿者名フラグがオン && 投稿者名が空
    if (hasContributorName && contributorName.isEmpty()) {
      context.buildConstraintViolationWithTemplate(message).addPropertyNode("contributorName")
          .addConstraintViolation();

      // バリデーションエラー
      return false;
    }

    return true;
  }
}
