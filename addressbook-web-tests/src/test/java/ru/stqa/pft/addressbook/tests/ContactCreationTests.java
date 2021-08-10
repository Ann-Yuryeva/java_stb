package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("John", "Kenny", "Johnas", "LA", "Johny","123456789","AT", "test1", "123456789","test@test.com"),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }

}
