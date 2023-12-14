package com.example.webjavaserver;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private List<String> friends;
    private int age;
    private String country;
    private List<String> groups;
    private String address;
    private String dateOfBirth;

    public User(int id, String name, String email, List<String> friends, int age, String country, List<String> groups,  String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.friends = friends;
        this.groups = groups;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }


    public User() {
        // Пустий конструктор за замовчуванням
    }

    public User(int id, String name, String email, List<String> friends, String dateOfBirth, String country, List<String> groups, String address) {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(country, user.country) &&
                Objects.equals(friends, user.friends) &&
                Objects.equals(groups, user.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, country,friends, groups);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", groups=" + groups +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", friends=" + friends +
                ", groups=" + groups +
                '}';
    }
}