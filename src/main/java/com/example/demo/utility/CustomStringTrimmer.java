package com.example.demo.utility;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;

public class CustomStringTrimmer extends PropertyEditorSupport {

  private final boolean emptyAsNull;

  public CustomStringTrimmer(boolean emptyAsNull) {
    this.emptyAsNull = emptyAsNull;
  }

  /**
   * ポスト時のみに呼び出される<br>
   * 全半角スペースのみの文字列をnullに変換する<br>
   * （@NotBlankは全角スペースがバリデーションエラー対象外のための対応）
   */
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
