package com.test.services;
import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.Service;

import com.test.beans.Car;
import com.test.beans.Employee;
import com.test.beans.Engine;
import com.test.beans.Employee;

import java.util.function.*;
@Service
public class AllServiceImpl {


	private static Map<Integer,Employee> mapEmployee = new HashMap<Integer,Employee>();
	private static Map<Integer,Car> mapCar = new HashMap<Integer,Car>();
	
	static {
		mapEmployee.put(1, new Employee("keyboard1", 1, 21, "engineer", 20000));
		mapEmployee.put(2, new Employee("keyboard2", 2, 22, "engineer", 20001));
		mapEmployee.put(3, new Employee("keyboard3", 3, 23, "engineer", 20002));
		mapEmployee.put(4, new Employee("keyboard4", 4, 24, "engineer", 20003));
		mapEmployee.put(5, new Employee("keyboard5", 5, 25, "engineer", 20004));
		mapEmployee.put(6, new Employee("keyboard6", 6, 20, "engineer", 20005));
		mapEmployee.put(7, new Employee("keyboard7", 7, 26, "engineer", 20006));
		mapEmployee.put(8, new Employee("keyboard7", 8, 26, "engineer", 20006));
		mapEmployee.put(9, new Employee("keyboard9", 9, 20, "engineer", 20005));
		mapEmployee.put(10, new Employee("keyboard10", 10, 20, "engineer", 20002));
		mapEmployee.put(11, new Employee("keyboard11", 11, 28, "engineer", 20001));
		mapEmployee.put(12, new Employee("keyboard12", 12, 19, "engineer", 20003));
		mapEmployee.put(13, new Employee("keyboard13", 13, 29, "engineer", 20004));
		mapEmployee.put(14, new Employee("keyboard9", 14, 27, "engineer", 20007));
		mapEmployee.put(15, new Employee("keyboard15", 15, 25, "engineer", 20005));
		mapEmployee.put(16, new Employee("keyboard16", 16, 24, "engineer", 20008));
		mapEmployee.put(17, new Employee("keyboard17", 17, 24, "engineer", 20009));
		mapEmployee.put(18, new Employee("keyboard9", 18, 23, "engineer", 20001));
		mapEmployee.put(19, new Employee("keyboard19", 19, 22, "engineer", 20004));
		mapEmployee.put(20, new Employee(null, 20, 29, "engineer", 20005));
		mapEmployee.put(21, new Employee("keyboard21", 21, 20, "engineer", 20005));
		mapEmployee.put(22, new Employee(null, 22, 25, "engineer", 20002));
		mapEmployee.put(23, new Employee("keyboard23", 23, 22, "engineer", 20003));
		mapEmployee.put(24, new Employee("keyboard24", 24, 27, "engineer", 20007));
		mapEmployee.put(25, null);
		mapEmployee.put(26, new Employee("keyboard26", 26, 28, "engineer", 20008));
		
		mapCar.put(1, new Car("maruti", "800", 790, Engine.PETROL));
		mapCar.put(2, new Car("maruti", "1000", 990, Engine.GAS));
		mapCar.put(3, new Car("maruti", "esteem", 997, Engine.PETROL));
		mapCar.put(4, new Car("maruti", "gypsy", 1110, Engine.GAS));
		mapCar.put(5, new Car("maruti", "omni", 840, Engine.PETROL));
		mapCar.put(6, new Car("tata", "indica", 880, Engine.GAS));
		mapCar.put(7, new Car("toyota", "qualis", 1510, Engine.PETROL));
		mapCar.put(8, new Car("tata", "sumo", 1505, Engine.GAS));
		mapCar.put(9, new Car("fiat", "padmini", 810, Engine.PETROL));
		mapCar.put(10, new Car("fiat", "uno", 995, Engine.GAS));
		mapCar.put(11, new Car("toyota", "corolla", 1300, Engine.PETROL));
		mapCar.put(12, new Car("Lamborghini", "lamborgini", 2600, Engine.GAS));
		mapCar.put(13, new Car("Mercedes", "maybach", 3400, Engine.PETROL));
		mapCar.put(14, new Car("BMW", "z4m40i", 4400, Engine.GAS));
		mapCar.put(15, new Car("Audi", "a7", 2900, Engine.PETROL));
			
	
	
	}


	public Optional<List<Employee>> getAllEmployees(){


		List<Employee> listEmployee = mapEmployee.entrySet().stream().filter(e->e.getValue()!=null).map(e->e.getValue()).toList();
		//listEmployee=null;
		
		return Optional.ofNullable(listEmployee);

	}

	
	
	
	public Optional<Employee> getEmployeeById(int id){
		Employee emp = mapEmployee.get(id);
		//System.out.println("!!!"+Optional.ofNullable(emp));
		Optional<Employee> opt = Optional.ofNullable(emp);
		return opt;
	}
	

	
	public Optional<Map<Integer,Car>> findAllCars(){
		return Optional.ofNullable(mapCar);
	}
	
	
	public Optional<Car> findCarById(int id){
		Car car = mapCar.get(id);
		return Optional.ofNullable(car);
	}
}
