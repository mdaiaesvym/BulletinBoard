package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MakeThreadService;

@Service
public class MakeThreadServiceImpl implements MakeThreadService{

	@Autowired
	private BulletinBoardMapper mapper;

	@Override
	public void makeThread(Thread thread) {
		mapper.insertThread(thread);
	}

	@Override
	public void addMessage(Message message) {
		mapper.insertMessage(message);
	}

	@Override
	public int getAutoIncrement() {
		return mapper.selectAutoIncrement();
	}

	@Override
	public void isContributorName(MakeThreadForm makethreadForm) {
		if(Objects.equals(makethreadForm.getCheckContributorName(), 0)) {
			makethreadForm.setContributorName("匿名");
		}
	}

	@Override
	public List<Thread> getThreadNameCount() {
		return mapper.selectThreadList();
	}

}
