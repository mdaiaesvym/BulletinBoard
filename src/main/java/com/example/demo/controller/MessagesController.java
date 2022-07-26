package com.example.demo.controller;

import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.MakeMessageForm;
import com.example.demo.model.Message;
import com.example.demo.service.MakeThreadService;
import com.example.demo.service.MessageService;

@Controller
public class MessagesController {

	@Autowired
	private MakeThreadService makeThreadService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 画面表示メソッド
	 * 
	 * @param model
	 * @param threadNumber
	 * @return
	 */
	@GetMapping("/messages/{threadNumber}")
	public String getMessages(Model model, @ModelAttribute MakeMessageForm form,
			@PathVariable("threadNumber") String threadNumber, RedirectAttributes redirectAttributes) {

		//スレッド数取得
		Integer threadCounts = messageService.getThreadCount();

		if (threadCounts.compareTo(Integer.valueOf(threadNumber)) >= 0 && Integer.valueOf(threadNumber) > 0) {

			// 対象スレッドのメッセージ一覧を取得
			List<Message> message = messageService.getMessageas(threadNumber);
			model.addAttribute("messageList", message);

			// スレッド名取得
			String threadName = messageService.getThreadName(threadNumber);
			model.addAttribute("threadName", threadName);

			model.addAttribute("threadNumber", threadNumber);

			return "/messages";
		}

		redirectAttributes.addFlashAttribute("errorMessage",
				messageSource.getMessage("messages.urlErrormessage", null, Locale.getDefault()));
		return "redirect:/threads";
	}

	/**
	 * メッセージ追加するメソッド
	 * 
	 * @param model
	 */
	@PostMapping(value = "/messages/{threadNumber}", params = "postMessage")
	public String postMessage(@ModelAttribute @Validated MakeMessageForm form, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return getMessages(model, form, form.getThreadNumber().toString(), redirectAttributes);
		}

		Message message = new Message();

		// 匿名・記名の確認
		messageService.isContributorName(form);
		// formをMessageクラスに変換
		message = modelMapper.map(form, Message.class);
		// テーブル「messages」に追加
		makeThreadService.addMessage(message);

		return "redirect:/messages/" + form.getThreadNumber();
	}
}
