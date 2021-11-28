package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {


 // @BeforeMethod
//  public void startMailServer() {
//    app.mail().start();
//  }

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
    }