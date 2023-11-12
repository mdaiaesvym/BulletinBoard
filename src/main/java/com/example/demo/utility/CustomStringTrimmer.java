package com.example.demo.utility;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;

public class CustomStringTrimmer extends PropertyEditorSupport {

  /**
   * ポスト時のみに呼び出されるメソッド<br>
   * 文字列内の余分な空白を削除<br>
   * 削除後、空文字のみであればnullに変換する<br>
   * （@NotBlank、@NotEmpty、@NotNullは全て全角スペースがバリデーションエラー対象外。<br>
   * 全て全角スペースをバリデーションエラーにするための対応を含む）
   */
  @Override
  public void setAsText(String text) {
    if (text == null) {
      this.setValue((Object) null);
    } else {
      String value = StringUtils.strip(text);

      if ("".equals(value)) {
        this.setValue((Object) null);
      } else {
        this.setValue(value);
      }
    }
  }
}
