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
  private final String NOTFOUND = "notFound";

  @ModelAttribute
  public void setMessages(@RequestParam("threadNumber") Integer threadNumber, Model model) {
    // 対象スレッドのメッセージ一覧を取得
    List<Message> messageList = messageService.getMessageList(threadNumber);
    model.addAttribute("messageList", messageList);
  }


  /**
   * 画面表示メソッド
   * 
   * @param form
   * @param model
   * @param redirectAttributes
   * @return
   */
  @SuppressWarnings("unchecked")
  @GetMapping(MESSAGES)
  public String getMessages(@ModelAttribute("makeMessageForm") MakeMessageForm form, Model model,
      RedirectAttributes redirectAttributes) {
    // メッセージ一覧をモデルから取得
    List<Message> messageList = (List<Message>) model.getAttribute("messageList");

    // メッセージがない場合（＝存在しないスレッドの場合）
    if (messageList.isEmpty()) {
      // エラー画面を表示
      return NOTFOUND;
    }

    // 共通処理呼び出し
    showCommon(model, form);

    return MESSAGES;
  }

  /**
   * メッセージ追加するメソッド
   * 
   * @param form
   * @param bindingResult
   * @param model
   * @param redirectAttributes
   * @return
   */
  @SuppressWarnings("unchecked")
  @PostMapping(value = MESSAGES, params = "postMessage")
  public String postMessage(@ModelAttribute("makeMessageForm") @Validated MakeMessageForm form,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
    // メッセージ一覧をモデルから取得
    List<Message> messageList = (List<Message>) model.getAttribute("messageList");

    // メッセージがない場合（＝存在しないスレッドの場合）
    if (messageList.isEmpty()) {
      // エラー画面を表示
      return NOTFOUND;
    }

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

    // スレッド名取得
    String threadName = messageService.getThreadName(threadNumber);
    model.addAttribute("threadName", threadName);
  }
}
