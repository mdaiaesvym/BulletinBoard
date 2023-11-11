package com.example.demo.utility;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * アプリケーション内のすべてのコントローラーに対する処理を提供
 */
@ControllerAdvice
public class BulletinBoardAdvice {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new CustomStringTrimmer(true));
  }
}
