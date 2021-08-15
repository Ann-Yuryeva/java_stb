package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobile("123456789")
            .withCompany("AT").withGroup("test1").withHome("123456789").withEmail("test@test.com");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
