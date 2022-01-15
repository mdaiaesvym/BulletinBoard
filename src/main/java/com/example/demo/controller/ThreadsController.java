package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThreadsController {

	@GetMapping("/threads")
	public String getThreads() {
		return "threads";
	}
}
