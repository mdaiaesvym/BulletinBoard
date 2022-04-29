package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MakeMessageForm {

	private Integer threadNumber;
	@NotBlank
	private String message;
	private Integer checkContributorName = 0;
	@NotBlank
	private String contributorName;
}
