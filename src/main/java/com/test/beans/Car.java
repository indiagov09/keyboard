package com.test.beans;
import java.util.*;
import java.util.stream.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
private String brand;
private String model;
private double power;
private Engine engine;
}



