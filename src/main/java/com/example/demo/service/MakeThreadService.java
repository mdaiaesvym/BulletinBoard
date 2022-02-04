package com.example.demo.service;

import com.example.demo.model.MakeThread;
import com.example.demo.model.Message;

public interface MakeThreadService {

	/**スレッド追加*/
	public void makeThread(MakeThread thread);
	
	/**メッセージ追加*/
	public void addMessage(Message message);
	
	/**オートインクリメント取得*/
	public int getAutoIncrement();
}
