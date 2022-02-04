package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.MakeThread;
import com.example.demo.model.Message;

@Mapper
public interface BulletinBoardMapper {

	/**スレッド追加*/
	public void insertThread(MakeThread thread);
	
	/**メッセージ追加*/
	public void insertMessage(Message message);
	
	/**オートインクリメント取得*/
	public int selectAutoIncrement();
}
