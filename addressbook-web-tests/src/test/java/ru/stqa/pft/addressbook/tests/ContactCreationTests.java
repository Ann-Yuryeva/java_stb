package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pftru.png");
    ContactData contact = new ContactData()
            .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
            .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
            .withCompany("AT").withHomePhone("123456789").withWorkPhone("123456789")
            .withEmail("111@test.com").withEmail2("222@test.com").withEmail3("333@test.com").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, CoreMatchers.equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
