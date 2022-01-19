package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.MakeThreadForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MakeThreadController {

	@GetMapping("/makeThread")
	public String getMakeThread(@ModelAttribute MakeThreadForm form) {
		return "makeThread";
	}
	
	@PostMapping("/threads")
	public String postMakeThred(@ModelAttribute MakeThreadForm form) {
		log.info(form.toString());
		
		return "threads";
	}
}
