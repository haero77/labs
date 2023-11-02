package com.labs.postingexample.domain;

public class Member {

    private int id;
    private int age;

    private Member(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public static Member ageOf(int age) {
        validateAgeOver20(age);
        return new Member(age, 0);
    }

    private static void validateAgeOver20(int age) {

    }

    public boolean ageEquals(int age) {
        return this.age == age;
    }

}
