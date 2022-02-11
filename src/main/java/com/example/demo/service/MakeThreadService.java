package com.example.demo.service;

import java.util.List;

import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;

public interface MakeThreadService {

	/**スレッド追加*/
	public void makeThread(Thread thread);
	
	/**スレッド名取得*/
	public List<Thread> getThreadNames();
	
	/**メッセージ追加*/
	public void addMessage(Message message);
	
	/**オートインクリメント取得*/
	public int getAutoIncrement();
	
	/**投稿者名の判定*/
	public void isContributorName(MakeThreadForm threadForm);
}
