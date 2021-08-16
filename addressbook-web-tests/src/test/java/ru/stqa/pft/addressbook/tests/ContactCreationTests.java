package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
   Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobile("123456789")
            .withCompany("AT").withGroup("test1").withHome("123456789").withEmail("test@test.com");
    app.contact().create(contact, true);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
