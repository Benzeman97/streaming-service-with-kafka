package com.benz.kafka.producer.api.model;

public class User {

    private int userId;
    private String name;
    private double salary;

    public User() {
    }

    public User(int userId, String name, double salary) {
        this.userId = userId;
        this.name = name;
        this.salary = salary;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
