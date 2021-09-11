package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("John").withMiddlename("Kenny").withLastname("Johnas")
              .withAddress("LA").withNickname("Johny").withMobilePhone("123456789")
              .withCompany("AT")
              .inGroup(groups.iterator().next())
              .withHomePhone("123456789").withWorkPhone("123456789")
              .withEmail("111@test.com").withEmail2("222@test.com").withEmail3("333@test.com").withPhoto(new File("src/test/resources/pftru.png")), true);
    } else if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    ContactData contact = selectContact();
    GroupData groupForDel = selectedGroup(contact);
    Groups before = contact.getGroups();
    app.goTo().HomePage();
    app.contact().selGroupForDel(groupForDel.getId());
    app.contact().removeContactFromGroup(contact, groupForDel.getId());
    ContactData contactsAfter = selectContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupForDel)));

  }

  private ContactData selectContactById(ContactData contact) {
    Contacts contactById = app.db().contacts();
    return contactById.iterator().next().withId(contact.getId());
  }

  private GroupData selectedGroup(ContactData deleteContact) {
    ContactData contact = selectContactById(deleteContact);
    Groups deletedContact = contact.getGroups();
    return deletedContact.iterator().next();
  }

  private ContactData selectContact() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }
    ContactData addContact = app.db().contacts().iterator().next();
    app.contact().addContactInGroup(addContact, app.db().groups().iterator().next());
    return addContact;
  }
}
