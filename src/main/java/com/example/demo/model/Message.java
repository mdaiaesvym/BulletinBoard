package com.example.demo.model;

import lombok.Data;

@Data
public class Message {

	private Integer threadNumber;
	private String message;
	private String contributor;
}
