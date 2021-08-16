package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobilePhone("123456789").withWorkPhone("123456789")
              .withCompany("AT").withGroup("test1").withHomePhone("123456789").withEmail("test@test.com"), true);;
    }
  }

  @Test (enabled = true)
  public void testContactPhones(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
