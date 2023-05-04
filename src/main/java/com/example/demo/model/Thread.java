package com.example.demo.model;

import java.util.Date;
import lombok.Data;

@Data
public class Thread {

  // スレッド番号
  private String threadNumber;
  // スレッド名
  private String threadName;
  // コメント数
  private Integer messageCount;
  // 更新日時
  private Date updatedAt;
}
