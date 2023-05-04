package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.controller.Utils.ControllerMessage;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.service.MakeThreadService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MakeThreadController {

  private final MakeThreadService makeThreadService;
  private final ModelMapper modelMapper;
  private final ControllerMessage controllerMessage;

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
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    // 入力チェック
    if (bindingResult.hasErrors()) {
      // 失敗メッセージ
      controllerMessage.addErrorMessage(model, "threads.postFailThread");

      return "/makeThread";
    }

    // formをTheradクラスに変換
    Thread thread = modelMapper.map(makeThreadForm, Thread.class);
    // スレッド作成処理
    makeThreadService.makeThread(thread);

    // 匿名・記名の確認
    makeThreadService.isContributorName(makeThreadForm);
    // formをMessageクラスに変換
    Message message = new Message();
    message = modelMapper.map(makeThreadForm, Message.class);
    // スレッド数を取得
    message.setThreadNumber(makeThreadService.getThreadMaxNumber());
    // メッセージ作成処理
    makeThreadService.addMessage(message);

    // 成功メッセージ
    controllerMessage.addInfoMessage(redirectAttributes, "threads.postSuccessThread");

    return "redirect:/threads";
  }
}
