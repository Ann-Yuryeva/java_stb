package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}

//TODO: Раскоммитеть после корректной работы FTP (лекция 8.5)
  // @BeforeMethod
//  public void startMailServer() {
//    app.mail().start();
//  }
//
//  @Test
//  public void testRegistration() throws IOException, MessagingException {
//    long now = System.currentTimeMillis();
//    String user = String.format("user%s", now);
//    String password = "password";
//    String email = String.format("user%s@localhost.localdomain", now);
////    app.james().createUser(user,password);
//    app.registration().start(user, email);
//    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
////    List<MailMessage> mailMessages = app.james().waitForMail(user,password, 60000);
//    String confirmationLink = findConfirmationLink(mailMessages, email);
//    app.registration().finish(confirmationLink, "password");
//    assertTrue(app.newSession().login(user, password));
//  }
//
//  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
//    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
//    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
//    return regex.getText(mailMessage.text);
//  }
//
//  @AfterMethod(alwaysRun = true)
//  public void stopMailServer() {
//    app.mail().stop();
//  }
//}

