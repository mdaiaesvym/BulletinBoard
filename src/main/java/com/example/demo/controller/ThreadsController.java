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
		
		//スレッド名取得
		List<Thread> threadNameList = makeThreadService.getThreadNames();
		//Modelに登録
		model.addAttribute("threadNameList",threadNameList);

		return "threads";
	}
}
