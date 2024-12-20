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
import com.example.demo.controller.utils.MessageUtil;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.service.MessageService;
import com.example.demo.service.ThreadService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MakeThreadController {

  private final ThreadService threadService;
  private final MessageService messageService;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  private final String MAKETHREAD = "makeThread";
  private final String THREADLIST = "threadList";

  /**
   * 画面表示メソッド
   * 
   * @param makeThreadForm
   * @return
   */
  @GetMapping(MAKETHREAD)
  public String show(@ModelAttribute("makeThreadForm") MakeThreadForm makeThreadForm) {
    return MAKETHREAD;
  }

  /**
   * スレッド作成メソッド
   * 
   * @param makeThreadForm
   * @param bindingResult
   * @param model
   * @param redirectAttributes
   * @return
   */
  @PostMapping(value = MAKETHREAD, params = "makeThread")
  public String postMakeThred(
      @ModelAttribute("makeThreadForm") @Validated MakeThreadForm makeThreadForm,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    // 入力チェック
    if (bindingResult.hasErrors()) {
      // 失敗メッセージ
      messageUtil.addErrorMessage(model, "threadList.postFailMessage");

      return MAKETHREAD;
    }

    // formをTheradクラスにマッピング
    Thread thread = modelMapper.map(makeThreadForm, Thread.class);

    // スレッドを作成し、スレッド番号を取得
    Integer threadNumber = threadService.makeThread(thread);

    if (threadNumber <= 0) {
      // 失敗メッセージ
      messageUtil.addErrorMessage(model, "threadList.postFailMessage");

      return MAKETHREAD;
    }

    // formをMessageクラスにマッピング
    Message message = new Message();
    message = modelMapper.map(makeThreadForm, Message.class);
    // スレッド番号を設定
    message.setThreadNumber(threadNumber);

    // メッセージ作成処理
    if (!messageService.addMessage(message)) {
      // 失敗メッセージ
      messageUtil.addErrorMessage(model, "threadList.postFailMessage");

      return MAKETHREAD;
    }
    // 成功メッセージ
    messageUtil.addInfoMessage(redirectAttributes, "threadList.postSuccessMessage");

    return "redirect:" + THREADLIST;

  }
}
