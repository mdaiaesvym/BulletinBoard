package com.example.demo.utility;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * アプリケーション内のすべてのコントローラーに対する共通処理
 */
@ControllerAdvice
public class BulletinBoardAdvice {

  /**
   * 各Controllerが呼び出される前の事前処理
   * 
   * @param binder
   */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new CustomStringTrimmer());
  }
}
