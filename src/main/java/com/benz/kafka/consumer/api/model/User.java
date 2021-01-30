package com.benz.kafka.consumer.api.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private int userId;
    private String name;
    private double salary;
}
