package com.learning.splitwise.splitwise.model;

public class User extends BaseModel{
	
	private String name;
    private String hashedPassword;
    private String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
