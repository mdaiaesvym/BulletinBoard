package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Message;
import com.example.demo.model.Thread;

@Mapper
public interface BulletinBoardMapper {

	/**スレッド追加*/
	public void insertThread(Thread thread);

	/**スレッド名取得*/
	public List<Thread> selectThreadNames();
	
	/**メッセージ追加*/
	public void insertMessage(Message message);
	
	/**オートインクリメント取得*/
	public int selectAutoIncrement();
	
}
