package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Thread;
import com.example.demo.service.MakeThreadService;

@Controller
public class ThreadsController {
	@Autowired
	private MakeThreadService makeThreadService;

	@GetMapping("/threads")
	public String getThreads(Model model) {
		
		//スレッド名・メッセージ数取得
		List<Thread> threadNameCount = makeThreadService.getThreadNameCount();
		//Modelに登録
		model.addAttribute("threadList",threadNameCount);
		
		return "threads";
	}
}
