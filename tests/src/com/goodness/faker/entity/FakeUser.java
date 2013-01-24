package com.goodness.faker.entity;

/**
 * User: Oleg Soroka
 * Date: 29.09.12
 * Time: 13:57
 */
public class FakeUser {

    private String login;
    private String name;
    private String surname;
    private String fullName;
    private String email;
    private String password;
    private String website;

    public FakeUser() {
        login = UserGen.getLogin();
        name = UserGen.getName();
        surname = UserGen.getSurname();
        fullName = String.format("%s %s", name, surname);
        email = UserGen.getEmail(login);
        password = UserGen.getPassword();
        website = UserGen.getWebsite(login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}