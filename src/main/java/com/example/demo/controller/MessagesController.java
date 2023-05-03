package com.example.demo.controller;

import java.util.List;
import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
  private final MessageSource messageSource;

  /**
   * 画面表示メソッド
   * 
   * @param model
   * @param threadNumber
   * @return
   */
  @GetMapping("/messages")
  public String getMessages(Model model, RedirectAttributes redirectAttributes,
      @ModelAttribute("makeMessageForm") MakeMessageForm makeMessageForm) {

    // スレッド数取得
    Integer threadCounts = messageService.getThreadCount();
    // スレッド番号取得
    String threadNumber = makeMessageForm.getThreadNumber();

    try {
      if (threadCounts.compareTo(Integer.valueOf(threadNumber)) >= 0
          && Integer.valueOf(makeMessageForm.getThreadNumber()) > 0) {

        // 対象スレッドのメッセージ一覧を取得
        List<Message> message = messageService.getMessageas(threadNumber);
        model.addAttribute("messageList", message);

        // スレッド名取得
        String threadName = messageService.getThreadName(threadNumber);
        model.addAttribute("threadName", threadName);

        model.addAttribute("threadNumber", threadNumber);

        return "/messages";
      }
    } catch (NumberFormatException e) {
      redirectAttributes.addFlashAttribute("errorMessage",
          messageSource.getMessage("threads.urlErrormessage", null, Locale.getDefault()));
      return "redirect:/threads";
    }

    redirectAttributes.addFlashAttribute("errorMessage",
        messageSource.getMessage("threads.urlErrormessage", null, Locale.getDefault()));
    return "redirect:/threads";
  }

  /**
   * メッセージ追加するメソッド
   * 
   * @param model
   */
  @PostMapping(value = "/messages", params = "postMessage")
  public String postMessage(
      @ModelAttribute("makeMessageForm") @Validated MakeMessageForm makeMessageForm,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

    // スレッド番号取得
    String threadNumber = makeMessageForm.getThreadNumber();

    // 入力チェック
    if (bindingResult.hasErrors()) {
      // 対象スレッドのメッセージ一覧を取得
      List<Message> message = messageService.getMessageas(threadNumber);
      model.addAttribute("messageList", message);

      // スレッド名取得
      String threadName = messageService.getThreadName(threadNumber);
      model.addAttribute("threadName", threadName);

      return "/messages";
    }

    Message message = new Message();

    // 匿名・記名の確認
    messageService.isContributorName(makeMessageForm);
    // formをMessageクラスに変換
    message = modelMapper.map(makeMessageForm, Message.class);
    // テーブル「messages」に追加
    makeThreadService.addMessage(message);

    // 成功メッセージ
    redirectAttributes.addFlashAttribute("postSuccessMessage",
        messageSource.getMessage("messages.postSuccessMessage", null, Locale.getDefault()));

    return "redirect:/messages";
  }
}
