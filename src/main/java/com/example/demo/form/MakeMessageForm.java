package com.example.demo.form;

import lombok.Data;

@Data
public class MakeMessageForm {
	
	private Integer threadNumber;
	private String message;
	private Integer checkContributorName=0;
	private String contributorName;
}
