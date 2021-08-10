package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("John", "Kenny", "Johnas", "LA", "Johny", "123456789", "AT", null,"123456789","test@test.com"),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
