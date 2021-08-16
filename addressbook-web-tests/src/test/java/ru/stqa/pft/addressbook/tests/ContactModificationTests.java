package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
              .withCompany("AT").withGroup("test1").withHomePhone("123456789").withEmail("test@test.com"), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
            .withCompany("AT").withGroup("test1").withHomePhone("123456789").withEmail("test@test.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
