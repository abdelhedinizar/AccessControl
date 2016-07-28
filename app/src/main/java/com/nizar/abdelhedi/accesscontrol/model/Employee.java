package com.nizar.abdelhedi.accesscontrol.model;

import java.util.Date;

/**
 * Created by abdelhedi on 29/06/2016.
 */
public class Employee {

    private int id;
    private String login;
    private String firstName;
    private String matricule;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Date birthDate;

    public Employee(String firstName) {
        this.firstName = firstName;
    }

    public Employee(String firstName,String lastName,String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
    }
    public Employee() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
