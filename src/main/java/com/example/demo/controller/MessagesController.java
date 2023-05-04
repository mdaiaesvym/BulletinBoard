package com.example.demo.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.controller.Utils.ControllerMessage;
import com.example.demo.form.MakeMessageForm;
import com.example.demo.model.Message;
import com.example.demo.service.MakeThreadService;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessagesController {

  private final MakeThreadService makeThreadService;
  private final MessageService messageService;
  private final ModelMapper modelMapper;
  private final ControllerMessage controllerMessage;

  /**
   * 画面表示メソッド
   * 
   * @param model
   * @param threadNumber
   * @return
   */
  @GetMapping("/messages")
  public String getMessages(Model model, RedirectAttributes redirectAttributes,
      @RequestParam(name = "threadNumber", required = false) String threadNumber,
      @ModelAttribute("makeMessageForm") MakeMessageForm form) {

    // スレッド数取得
    Integer threadCounts = messageService.getThreadCount();

    // 存在するページにアクセスした場合
    if (threadCounts.compareTo(Integer.valueOf(threadNumber)) >= 0
        && Integer.valueOf(form.getThreadNumber()) > 0) {
      // 共通処理呼び出し
      showCommon(model, form);

      return "/messages";
    } else {
      // 失敗メッセージ
      controllerMessage.addErrorMessage(redirectAttributes, "threads.urlErrormessage");

      return "redirect:/threads";
    }
  }

  /**
   * メッセージ追加するメソッド
   * 
   * @param model
   */
  @PostMapping(value = "/messages", params = "postMessage")
  public String postMessage(@ModelAttribute("makeMessageForm") @Validated MakeMessageForm form,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    // 入力チェック
    if (bindingResult.hasErrors()) {
      // 共通処理呼び出し
      showCommon(model, form);

      // 失敗メッセージ
      controllerMessage.addErrorMessage(model, "messages.postFailMessage");

      return "/messages";
    }

    Message message = new Message();

    // 匿名・記名の確認
    messageService.isContributorName(form);
    // formをMessageクラスに変換
    message = modelMapper.map(form, Message.class);
    // メッセージ追加処理
    makeThreadService.addMessage(message);

    // リダイレクト用にスレッド番号を設定
    redirectAttributes.addAttribute("threadNumber", form.getThreadNumber());

    // 成功メッセージ
    controllerMessage.addInfoMessage(redirectAttributes, "messages.postSuccessMessage");

    return "redirect:/messages";
  }

  /**
   * 画面表示の共通処理
   * 
   * @param model
   * @param form
   */
  private void showCommon(Model model, MakeMessageForm form) {
    // スレッド番号取得
    String threadNumber = form.getThreadNumber();
    model.addAttribute("threadNumber", threadNumber);

    // 対象スレッドのメッセージ一覧を取得
    List<Message> message = messageService.getMessageas(threadNumber);
    model.addAttribute("messageList", message);

    // スレッド名取得
    String threadName = messageService.getThreadName(threadNumber);
    model.addAttribute("threadName", threadName);
  }
}
