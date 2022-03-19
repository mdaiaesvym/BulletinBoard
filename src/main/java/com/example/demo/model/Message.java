package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class Message {

	private Integer threadNumber;
	private String message;
	private Date updatedAt; 
	private String contributorName;
}
