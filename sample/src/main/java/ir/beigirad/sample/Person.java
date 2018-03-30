package ir.beigirad.sample;

import com.google.gson.annotations.SerializedName;

public class Person{

	@SerializedName("first-name")
	public String firstName;

	@SerializedName("last-name")
	public String lastName;

}