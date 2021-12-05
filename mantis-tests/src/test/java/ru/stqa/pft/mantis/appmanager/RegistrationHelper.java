package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start (String username, String email){
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    wd.findElement(By.name("username")).sendKeys(username);
  }
}

// TODO: Раскоммитеть после корректной работы FTP (лекция 8.5)
//public class RegistrationHelper extends HelperBase {
//
//  public RegistrationHelper(ApplicationManager app) {
//    super(app);
//  }
//
//  public void start(String username, String email) {
//    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
//    type(By.name("username"), username);
//    type(By.name("email"), email);
//    click(By.cssSelector("input[value='Signup']"));
//  }
//
//  public void finish(String confirmationLink, String password) {
//    wd.get(confirmationLink);
//    type(By.name("password"), password);
//    type(By.name("password_confirm"), password);
//    click(By.cssSelector("input[value='Update User']"));
//  }
//}
