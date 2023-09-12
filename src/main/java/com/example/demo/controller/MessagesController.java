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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.controller.utils.MessageUtil;
import com.example.demo.form.MakeMessageForm;
import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessagesController {

  private final MessageService messageService;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  private final String MESSAGES = "messages";
  private final String THREADS = "threads";

  /**
   * 画面表示メソッド
   * 
   * @param model
   * @param redirectAttributes
   * @param form
   * @return
   */
  @GetMapping(MESSAGES)
  public String getMessages(Model model, RedirectAttributes redirectAttributes,
      @ModelAttribute("makeMessageForm") MakeMessageForm form) {

    // スレッド番号一覧を取得
    List<Integer> threadNumberList = messageService.getThreadNumberList();

    boolean notExist =
        threadNumberList.stream().noneMatch(item -> item.equals(form.getThreadNumber()));

    // 存在しないページにアクセスした場合
    if (notExist) {
      // 失敗メッセージ
      messageUtil.addErrorMessage(redirectAttributes, "threads.notFoundPageMessage");

      return "redirect:" + THREADS;
    }
    // 共通処理呼び出し
    showCommon(model, form);

    return MESSAGES;
  }

  /**
   * メッセージ追加するメソッド
   * 
   * @param model
   */
  @PostMapping(value = MESSAGES, params = "postMessage")
  public String postMessage(@ModelAttribute("makeMessageForm") @Validated MakeMessageForm form,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    // 入力チェック
    if (bindingResult.hasErrors()) {
      // 共通処理呼び出し
      showCommon(model, form);

      // 失敗メッセージ
      messageUtil.addErrorMessage(model, "messages.postFailMessage");

      return MESSAGES;
    }

    // formをMessageクラスにマッピング
    Message message = new Message();
    message = modelMapper.map(form, Message.class);

    // メッセージ追加処理
    if (!messageService.addMessage(message)) {
      // 共通処理呼び出し
      showCommon(model, form);

      // 失敗メッセージ
      messageUtil.addErrorMessage(model, "messages.postFailMessage");

      return MESSAGES;
    }
    // 成功メッセージ
    messageUtil.addInfoMessage(redirectAttributes, "messages.postSuccessMessage");
    // リダイレクト用にスレッド番号を設定
    redirectAttributes.addAttribute("threadNumber", form.getThreadNumber());

    return "redirect:" + MESSAGES;
  }

  /**
   * 画面表示の共通処理
   * 
   * @param model
   * @param form
   */
  private void showCommon(Model model, MakeMessageForm form) {
    // スレッド番号取得
    Integer threadNumber = form.getThreadNumber();
    model.addAttribute("threadNumber", threadNumber);

    // 対象スレッドのメッセージ一覧を取得
    List<Message> messageList = messageService.getMessageList(threadNumber);
    model.addAttribute("messageList", messageList);

    // スレッド名取得
    String threadName = messageService.getThreadName(threadNumber);
    model.addAttribute("threadName", threadName);
  }
}
