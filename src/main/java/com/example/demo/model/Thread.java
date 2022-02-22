package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class Thread {

	private String threadName;
	private Integer messageCount;
	private Date updatedAt;
}
