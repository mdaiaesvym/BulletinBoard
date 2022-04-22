package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	 * @param form
	 * @return
	 */
	@GetMapping("/makeThread")
	public String getMakeThread(@ModelAttribute MakeThreadForm form) {
		return "/makeThread";
	}

	/**
	 * スレッド作成メソッド
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(value = "/threads", params = "makeThread")
	public String postMakeThred(@ModelAttribute MakeThreadForm form) {

		Message message = new Message();

		// formをTheradクラスに変換
		Thread thread = modelMapper.map(form, Thread.class);
		// テーブル「threads」に追加
		makeThreadService.makeThread(thread);

		// 匿名・記名の確認
		makeThreadService.isContributorName(form);
		// formをMessageクラスに変換
		message = modelMapper.map(form, Message.class);
		// オートインクリメント取得
		message.setThreadNumber(makeThreadService.getAutoIncrement());
		// テーブル「messages」に追加
		makeThreadService.addMessage(message);

		return "redirect:/threads";
	}
}
