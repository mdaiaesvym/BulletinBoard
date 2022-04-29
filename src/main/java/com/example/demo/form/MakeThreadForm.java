package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MakeThreadForm {

	@NotBlank
	private String threadName;
	@NotBlank
	private String message;
	private Integer checkContributorName = 0;
	@NotBlank
	private String contributorName;
}
