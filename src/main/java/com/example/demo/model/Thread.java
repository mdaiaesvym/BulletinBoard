package com.example.demo.model;

import java.util.Date;
import lombok.Data;

@Data
public class Thread {

  // スレッド番号
  private Integer threadNumber;
  // スレッド名
  private String threadName;
  // 更新日時
  private Date updatedAt;
  // コメント数
  private Integer messageCount;
}
