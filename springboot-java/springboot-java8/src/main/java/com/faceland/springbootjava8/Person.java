package com.faceland.springbootjava8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author watermelon
 * @Date 2020-02-19
 * @Description
 */
@Data
@Accessors(chain = true)
public class Person {
    private String name;
    private Integer age;
    private Double weight;

    public Person(String name, int age, double weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
    }
}
