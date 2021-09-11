package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInAGroup extends TestBase {

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
  public void testAddContactInGroup() {
    app.goTo().HomePage();
    ContactData contact = selectedContact();
    Groups before = contact.getGroups();
    GroupData groupForAdd = selectedGroup(contact);
    app.contact().addContactInGroup(contact, groupForAdd);
    Groups after = app.db().getContactFromDb(contact.getId()).getGroups();
    equalTo(before.withAdded(groupForAdd));
  }

  public ContactData selectedContact() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < groups.size()) {
        return contact;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1"));
    app.goTo().HomePage();
    return contacts.iterator().next();
  }

  public GroupData selectedGroup(ContactData contact) {
    Groups all = app.db().groups();
    Collection<GroupData> freeGroups = new HashSet<GroupData>(all);
    freeGroups.removeAll(contact.getGroups());
    return freeGroups.iterator().next();
  }
}
