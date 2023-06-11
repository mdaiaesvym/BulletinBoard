package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.controller.utils.MessageUtil;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OriginalErrorController implements ErrorController {
  private final MessageUtil messageUtil;

  private final String THREADS = "threads";

  @RequestMapping("/error")
  public String redirect(RedirectAttributes redirectAttributes) {
    // メッセージ設定
    messageUtil.addErrorMessage(redirectAttributes, "urlNotFound");

    // 存在しないページにアクセスしたときは、掲示板一覧画面に戻る
    return "redirect:" + THREADS;
  }
}
