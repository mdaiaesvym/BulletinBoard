package com.example.demo.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.service.impl.MakeThreadServiceImpl;

public class MakeThreadServiceImplTest {

  MakeThreadService makeThreadServiceImpl = new MakeThreadServiceImpl();

  @SuppressWarnings("deprecation")
  @Test
  public void 値が0ならば匿名() {
    MakeThreadForm makethreadForm = new MakeThreadForm();
    makethreadForm.setIsContributorName(0);

    makeThreadServiceImpl.isContributorName(makethreadForm);
    assertThat(makethreadForm.getContributorName(), is("匿名"));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void 値が1ならば設定値() {
    MakeThreadForm makethreadForm = new MakeThreadForm();
    makethreadForm.setIsContributorName(1);
    makethreadForm.setContributorName("テスト投稿者");

    makeThreadServiceImpl.isContributorName(makethreadForm);
    assertThat(makethreadForm.getContributorName(), is("テスト投稿者"));
  }
}
