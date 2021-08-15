package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobile("123456789")
              .withCompany("AT").withGroup("test1").withHome("123456789").withEmail("test@test.com"), true);
    }
  }


  @Test(enabled = true)
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
