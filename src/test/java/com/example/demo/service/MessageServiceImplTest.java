package com.example.demo.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import com.example.demo.form.MakeMessageForm;
import com.example.demo.service.impl.MessageServiceImpl;

public class MessageServiceImplTest {
  MessageService messageServiceImpl = new MessageServiceImpl();

  @SuppressWarnings("deprecation")
  @Test
  public void 値が0ならば匿名() {
    MakeMessageForm makeMessageForm = new MakeMessageForm();
    makeMessageForm.setHasContributorName(false);

    messageServiceImpl.setContributorName(makeMessageForm);
    assertThat(makeMessageForm.getContributorName(), is("匿名"));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void 値が1ならば設定値() {
    MakeMessageForm makeMessageForm = new MakeMessageForm();
    makeMessageForm.setHasContributorName(true);
    makeMessageForm.setContributorName("テスト投稿者");

    messageServiceImpl.setContributorName(makeMessageForm);
    assertThat(makeMessageForm.getContributorName(), is("テスト投稿者"));
  }
}
