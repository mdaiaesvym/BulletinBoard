package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.service.MakeThreadService;

@Controller
public class MakeThreadController {

  @Autowired
  private MakeThreadService makeThreadService;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * 画面表示メソッド
   * 
   * @param makeThreadForm
   * @return
   */
  @GetMapping("/makeThread")
  public String getMakeThread(@ModelAttribute("makeThreadForm") MakeThreadForm makeThreadForm) {
    return "/makeThread";
  }

  /**
   * スレッド作成メソッド
   * 
   * @param makeThreadForm
   * @return
   */
  @PostMapping(value = "/makeThread", params = "makeThread")
  public String postMakeThred(
      @ModelAttribute("makeThreadForm") @Validated MakeThreadForm makeThreadForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return getMakeThread(makeThreadForm);
    }

    Message message = new Message();

    // formをTheradクラスに変換
    Thread thread = modelMapper.map(makeThreadForm, Thread.class);
    // テーブル「threads」に追加
    makeThreadService.makeThread(thread);

    // 匿名・記名の確認
    makeThreadService.isContributorName(makeThreadForm);
    // formをMessageクラスに変換
    message = modelMapper.map(makeThreadForm, Message.class);
    // オートインクリメント取得
    message.setThreadNumber(makeThreadService.getAutoIncrement());
    // テーブル「messages」に追加
    makeThreadService.addMessage(message);

    return "redirect:/threads";
  }
}
