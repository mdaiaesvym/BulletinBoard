package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MakeThread {

	@GetMapping("/makethread")
	public String getMakeThread() {
		return "makethread";
	}
}
