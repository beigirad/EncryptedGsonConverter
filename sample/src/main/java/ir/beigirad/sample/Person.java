package ir.beigirad.sample;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("first-name")
    public String firstName;

    @SerializedName("last-name")
    public String lastName;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + "\'\n" +
                ", lastName='" + lastName + "\'\n" +
                '}';
    }
}