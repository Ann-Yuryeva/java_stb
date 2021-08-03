package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("John", "Kenny", "Johnas", "LA", "Johny", "123456789", "AT", "123456789","test@test.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }

}
