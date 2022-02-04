package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MakeThread;
import com.example.demo.model.Message;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MakeThreadService;

@Service
public class MakeThreadServiceImpl implements MakeThreadService{

	@Autowired
	private BulletinBoardMapper mapper;

	@Override
	public void makeThread(MakeThread thread) {
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

}
