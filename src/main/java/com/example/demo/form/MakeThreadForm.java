package com.example.demo.form;

import lombok.Data;

@Data
public class MakeThreadForm {

	private String threadName;
	private String message;
	private Integer whoami=0;
}
