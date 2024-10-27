package com.test.beans;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
public class Employee {
private String name;
private int id;
private int age;
private String designation;
private int salary;

public Employee() {
}



public Employee(String name, int id, int age, String designation, int salary) {
	this.name = name;
	this.id = id;
	this.age = age;
	this.designation = designation;
	this.salary = salary;
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}



@Override
public int hashCode() {
	return Objects.hash(name);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (this.getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;
	return Objects.equals(name, other.name);
}

}
