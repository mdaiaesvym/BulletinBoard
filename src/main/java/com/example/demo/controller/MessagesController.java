package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;

@Controller
public class MessagesController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("/messages/{threadNumber}")
	public String getMessages(Model model,@PathVariable("threadNumber")String threadNumber) {
		
		/**対象スレッドのメッセージ一覧を取得*/
		List<Message> message = messageService.getMessageas(threadNumber);
		model.addAttribute("messageList", message);
		
		return "messages";
	}
}
