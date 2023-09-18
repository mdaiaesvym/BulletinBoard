package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OriginalErrorController implements ErrorController {
  private final String NOTFOUND = "notFound";

  /**
   * 存在しないページにアクセスしたときの処理
   * 
   * @param redirectAttributes
   * @return
   */
  @GetMapping("/error")
  public String show(RedirectAttributes redirectAttributes) {

    // エラー画面を表示
    return NOTFOUND;
  }
}
