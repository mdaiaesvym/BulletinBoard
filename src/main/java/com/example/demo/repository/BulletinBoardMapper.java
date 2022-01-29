package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.MakeThread;

@Mapper
public interface BulletinBoardMapper {

	/**スレッド追加*/
	public void insertThread(MakeThread thread);
}
