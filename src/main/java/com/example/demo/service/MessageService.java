package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Message;

public interface MessageService {

	/**メッセージ一覧取得*/
	public List<Message> getMessageas(String threadNumber);
}
