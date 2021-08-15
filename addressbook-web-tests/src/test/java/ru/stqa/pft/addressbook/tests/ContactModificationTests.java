package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobile("123456789")
              .withCompany("AT").withGroup("test1").withHome("123456789").withEmail("test@test.com"), true);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobile("123456789")
            .withCompany("AT").withGroup("test1").withHome("123456789").withEmail("test@test.com");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
