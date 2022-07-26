package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.MakeMessageForm;
import com.example.demo.model.Message;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private BulletinBoardMapper mapper;

	@Override
	public List<Message> getMessageas(String threadNumber) {
		return mapper.selectMessageList(threadNumber);
	}

	@Override
	public String getThreadName(String threadNumber) {
		return mapper.selectThreadName(threadNumber);
	}

	@Override
	public void isContributorName(MakeMessageForm makeMessageForm) {
		if (Objects.equals(makeMessageForm.getCheckContributorName(), 0)) {
			makeMessageForm.setContributorName("匿名");
		}
	}

	@Override
	public Integer getThreadCount() {
		return mapper.selectThreadCount();
	}
}
