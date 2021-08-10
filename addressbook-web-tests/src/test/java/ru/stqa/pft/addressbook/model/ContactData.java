package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String nickname;
  private final String home;
  private final String company;
  private String group;
  private final String mobile;
  private final String email;

  public ContactData(String firstname, String middlename, String lastname, String address, String nickname, String home, String company,String group, String mobile, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.nickname = nickname;
    this.home = home;
    this.company = company;
    this.group = group;
    this.mobile = mobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getNickname() {
    return nickname;
  }

  public String getHome() {
    return home;
  }

  public String getCompany() {
    return company;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group;  }
}
