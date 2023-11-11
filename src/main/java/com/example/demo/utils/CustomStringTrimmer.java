package com.example.demo.utils;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;

/**
 * 空（全半角スペース含む）の文字列をnullに変換する<br>
 * （@NotBlankは全角スペースがバリデーションエラー対象外のための対応）
 */
public class CustomStringTrimmer extends PropertyEditorSupport {

  private final boolean emptyAsNull;

  public CustomStringTrimmer(boolean emptyAsNull) {
    this.emptyAsNull = emptyAsNull;
  }

  @Override
  public String getAsText() {
    Object value = this.getValue();
    return value != null ? value.toString() : "";
  }

  @Override
  public void setAsText(String text) {
    if (text == null) {
      this.setValue((Object) null);
    } else {
      String value = StringUtils.strip(text);

      if (this.emptyAsNull && "".equals(value)) {
        this.setValue((Object) null);
      } else {
        this.setValue(value);
      }
    }
  }
}
