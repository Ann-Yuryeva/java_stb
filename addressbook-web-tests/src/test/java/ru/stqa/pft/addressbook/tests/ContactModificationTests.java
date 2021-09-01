package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
              .withCompany("AT").withGroup("test1").withHomePhone("123456789").withWorkPhone("123456789")
              .withEmail("111@test.com").withEmail2("222@test.com").withEmail3("333@test.com").withPhoto(new File("src/test/resources/pftru.png")), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
            .withCompany("AT").withGroup("test1").withHomePhone("123456789").withWorkPhone("123456789")
            .withEmail("111@test.com").withEmail2("222@test.com").withEmail3("333@test.com").withPhoto(new File("src/test/resources/pftru.png"));
    app.goTo().HomePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
