package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.MakeThread;
import com.example.demo.service.MakeThreadService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MakeThreadController {

	@Autowired
	private MakeThreadService makeThreadService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/makeThread")
	public String getMakeThread(@ModelAttribute MakeThreadForm form) {
		return "makeThread";
	}
	
	@PostMapping("/threads")
	public String postMakeThred(@ModelAttribute MakeThreadForm form) {
		log.info(form.toString());
		
		//formをMakeTheradクラスに変換
		MakeThread thread = modelMapper.map(form,MakeThread.class);
		makeThreadService.makeThread(thread);
		
		return "threads";
	}
}
