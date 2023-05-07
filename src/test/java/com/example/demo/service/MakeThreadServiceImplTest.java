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
  public void 値がfalseならば匿名() {
    MakeThreadForm makethreadForm = new MakeThreadForm();
    makethreadForm.setHasContributorName(false);

    makeThreadServiceImpl.setContributorName(makethreadForm);
    assertThat(makethreadForm.getContributorName(), is("匿名"));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void 値がtrueならば設定値() {
    MakeThreadForm makethreadForm = new MakeThreadForm();
    makethreadForm.setHasContributorName(true);
    makethreadForm.setContributorName("テスト投稿者");

    makeThreadServiceImpl.setContributorName(makethreadForm);
    assertThat(makethreadForm.getContributorName(), is("テスト投稿者"));
  }
}
