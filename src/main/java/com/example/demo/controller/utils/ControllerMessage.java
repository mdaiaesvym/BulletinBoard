package com.example.demo.controller.utils;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ControllerMessage {

  private final MessageSource messageSource;

  /**
   * 成功メッセージの追加
   * 
   * @param redirectAttributes
   * @param message
   */
  public void addInfoMessage(RedirectAttributes redirectAttributes, String message) {
    redirectAttributes.addFlashAttribute("infoMessage",
        messageSource.getMessage(message, null, Locale.getDefault()));
  }

  /**
   * 失敗メッセージの追加
   * 
   * @param model
   * @param message
   */
  public void addErrorMessage(Model model, String message) {
    model.addAttribute("errorMessage",
        messageSource.getMessage(message, null, Locale.getDefault()));
  }


  /**
   * 失敗メッセージの追加
   * 
   * @param redirectAttributes
   * @param message
   */
  public void addErrorMessage(RedirectAttributes redirectAttributes, String message) {
    redirectAttributes.addFlashAttribute("errorMessage",
        messageSource.getMessage(message, null, Locale.getDefault()));
  }
}
