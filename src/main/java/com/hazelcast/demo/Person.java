package com.hazelcast.demo;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import java.io.Serializable;

public class Person implements Serializable {
    private String lastName;
    private String firstName;
    private String street1;
    private String city;
    private String state;
    private String zip;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", street1='" + street1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }


    private static final Faker fake = Faker.instance();

    public static Person fakePerson(){
        Name n = fake.name();
        Address address = fake.address();
        Person result = new Person();
        result.setLastName(n.lastName());
        result.setFirstName(n.firstName());
        result.setStreet1(address.streetAddress());
        result.setCity(address.city());
        result.setState(address.stateAbbr());
        result.setZip(address.zipCodeByState(result.getState()));
        return result;
    }
}
