package com.test.controllers;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.beans.Car;
import com.test.beans.Employee;
import com.test.beans.MyException;
import com.test.services.AllServiceImpl;

import java.util.function.*;
@RestController
public class KBController {

	@Autowired
	private AllServiceImpl service;

	@GetMapping(value="api/test")
	public ResponseEntity<?> test(){
		Optional<List<Employee>> allEmployees = service.getAllEmployees();
		ResponseEntity<?> responseEntity = null;
		if(allEmployees.isPresent()) {

			List<Employee> list = allEmployees.get();


			responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}




	@GetMapping(value="api/test/{id}")
	public ResponseEntity<?> test_id(@PathVariable(name = "id")int id){
		Optional<Employee> optEmployee = service.getEmployeeById(id);
		ResponseEntity<?> responseEntity = null;

		responseEntity = optEmployee.map(e->{
			String nn = e.getName();
			Optional<String> optStr = Optional.ofNullable(nn);
			return optStr.map(str->new ResponseEntity<>(str.toUpperCase(),HttpStatus.OK))
					.orElse(new ResponseEntity<>("no name found",HttpStatus.NOT_FOUND) );
		}).orElse(new ResponseEntity<>("no employee found", HttpStatus.NOT_FOUND) );

		return responseEntity;


		//		if(optEmployee.isPresent()) {
		//			Employee emp = optEmployee.get();
		//			String name = emp.getName();
		//			Optional<String> optStr = Optional.ofNullable(name);
		////			if(optStr.isPresent()) {
		////				responseEntity = new ResponseEntity<>(name.toUpperCase(),HttpStatus.OK);	
		////			}
		////			else {
		////				responseEntity = new ResponseEntity<>("name not found",HttpStatus.NOT_FOUND);
		////			}
		//		
		//			responseEntity = optStr.map(str-> { 
		//				
		//				System.out.println(str+" ####");
		//				
		//				return new ResponseEntity<>(str.toUpperCase(),HttpStatus.OK);
		//				
		//			}).orElse(new ResponseEntity<>("no name found",HttpStatus.NOT_FOUND) );
		//		}
		//		else {
		//			responseEntity = new ResponseEntity<>("no employee found", HttpStatus.NOT_FOUND);
		//		}
		//		

		//return responseEntity;
	}


	@GetMapping("api/test/all/brands")
	public ResponseEntity<?> getAllBrands(){
		System.out.println("hello");
		Optional<Map<Integer,Car>> optCars =  service.findAllCars();
		//		if(optCars.isPresent()) {
		//			Map<Integer,Car> mapCars = optCars.get();
		//			Set<String> set=new HashSet<String>();
		//			List<String> duplicates = mapCars.entrySet().stream().map(e->e.getValue().getBrand()).filter(str->!set.add(str)).toList();
		//
		//			return new ResponseEntity<>(set,HttpStatus.OK);
		//
		//		}
		
		
//		Set<String> duplicates = optCars.map(carMap->{
//			Set<String> set = new HashSet<String>();
//			return carMap.entrySet().stream().map(e->e.getValue().getBrand()).filter(str->!set.add(str)).collect(Collectors.toSet());
//		}).orElseGet(()->testSupplier());
		
		
		return new ResponseEntity<>(optCars.map(carMap->{
			Set<String> set = new HashSet<String>();
			return carMap.entrySet().stream().map(e->e.getValue().getBrand()).filter(str->!set.add(str)).collect(Collectors.toSet());
		}).orElseGet(()->testSupplier()),HttpStatus.OK);
		
		//return new ResponseEntity<>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);


	}

	private Set<String> testSupplier(){
		Set<String> set = new HashSet<String>();
		set.add("no elements");
		return set;
	}

	@GetMapping("api/test/all/brands/frequency")
	public ResponseEntity<?> getAllBrandsFrequency(){
		Optional<Map<Integer,Car>> optCars =  service.findAllCars();
		if(optCars.isPresent()) {
			Map<Integer,Car> mapCars = optCars.get();


			Map<String,Long> map = mapCars.entrySet().stream().map(e->e.getValue().getBrand()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));


			return new ResponseEntity<>(map,HttpStatus.OK);

		}
		return new ResponseEntity<>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);


	}



	@GetMapping("api/test/all/brands/ultra")
	public ResponseEntity<?> getAllBrandsUltra(){
		Optional<Map<Integer,Car>> optCars =  service.findAllCars();
		if(optCars.isPresent()) {
			Map<Integer,Car> mapCars = optCars.get();


			Map<String,Long> map = mapCars.entrySet().stream().map(e->e.getValue().getBrand()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));


			List<String> list = map.entrySet().stream().filter(e->e.getValue()==1).map(e->e.getKey()).toList();


			return new ResponseEntity<>(list,HttpStatus.OK);

		}
		return new ResponseEntity<>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);


	}




	@GetMapping("api/test/all/sorted/power")
	public ResponseEntity<?> getAllSortedPower(){
		Optional<Map<Integer,Car>> optCars =  service.findAllCars();
		if(optCars.isPresent()) {
			Map<Integer,Car> mapCars = optCars.get();

			Map<String, Double> collect = mapCars.entrySet().stream()
					.sorted((o1,o2)-> (int)(o1.getValue().getPower() - o2.getValue().getPower()))
					.collect(Collectors
							.toMap(e->e.getValue().getModel(), e->e.getValue().getPower(),(e1,e2)->e1,LinkedHashMap::new));

			//			    Map<String,Double> collect2 = collect.entrySet().stream()
			//			    		.sorted((o1,o2)->(int)(o1.getValue()-o2.getValue()))
			//			    		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));

			return new ResponseEntity<>(collect,HttpStatus.OK);

		}
		return new ResponseEntity<>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
	}








	@GetMapping("api/test/top/3/salary")
	public List<Employee> getTop3Salary(){
		Optional<List<Employee>> optEmployees =  service.getAllEmployees();
		List<Employee> response =  optEmployees.map(e->{
			List<Employee> list = e.stream().sorted((o1,o2)-> o2.getSalary()-o1.getSalary()).limit(3).toList();
			return list;
		}).orElseThrow();
		return response;
	}




	@GetMapping("api/test/summary/statistics/highest/salary")
	public Integer getHighestSalary(){
		Optional<List<Employee>> allEmployees = service.getAllEmployees();
		int x =  allEmployees.map(ls->{
			return ls.stream().mapToInt(emp->emp.getSalary()).summaryStatistics().getMax();
		}).orElseGet(()->0);

		return x;
	}


	@GetMapping("api/test/arrays/distinct/sorted")
	public int[] arraysdistinct(){
		int[] arr = {2,5,1,2,8,5,4};
		int[] arr2 =Arrays.stream(arr).distinct().sorted().toArray();
		return arr2;
	}
	@GetMapping("api/test/second/smallest")
	public Integer secondsmallest(){
		int[] arr = {2,5,1,2,8,5,4};
		int num =Arrays.stream(arr).distinct().sorted().skip(1).findFirst().orElseThrow(()->new IllegalArgumentException("wrong"));
		return num;
	}
	@GetMapping("api/test/duplicates/elements/two/array")
	public List<Integer> findduplicatesinarray(){
		int[] arr1 = {2,5,1,2,8,5,4};
		int[] arr2 = {22,55,1,2,88,55,44};
		List<Integer> list_ = Arrays.stream(arr1).filter(num1->Arrays.stream(arr2).anyMatch(num2->num2==num1)).distinct().boxed().collect(Collectors.toList());
		Optional<List<Integer>> ofNullable = Optional.ofNullable(list_);
		List<Integer> list = ofNullable.map(lst->lst).orElseThrow();
		return list;	
	}
	@GetMapping("api/test/duplicate/frequency/array")
	public Map<Integer,Long> duplicatefrequencyarray(){
		int[] arr1 = {2,5,1,2,8,5,4};
		//int[] arr1 = null;
		int[] arr1_ = Optional.ofNullable(arr1).orElseThrow();// throws noSuchElementException
		Map<Integer,Long> map = Arrays.stream(arr1_).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Optional<Map<Integer,Long>> ofNullable = Optional.ofNullable(map);
		//LinkedHashMap<Integer, Long> map_ = ofNullable.map(e->e.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new))).orElseThrow();
		Map<Integer, Long> map_ = ofNullable.map(e->e).orElseThrow();
		return map_;
	}

	@GetMapping("api/test/groupby/employee/age")
	public Map<Integer,Set<Employee>> groupbyemployeeage(){
		Optional<List<Employee>> allEmployees = service.getAllEmployees();
		Optional<List<Employee>> allEmployeesOptional = Optional.ofNullable(allEmployees).orElseThrow();
		return allEmployeesOptional.map(list->{
			Map<Integer, Set<Employee>> uniqueAgeGrouper = list.stream().collect(Collectors.groupingBy(emp->emp.getAge(),TreeMap::new,Collectors.toSet()));
			return uniqueAgeGrouper;
		}).orElseThrow();
	}
	@GetMapping("api/test/summarizeInt")
	public Map<String,Integer> testsummerizeint(){
		Optional<List<Employee>> optEmployees = service.getAllEmployees();
		List<Integer> lsAges = optEmployees.map(ls->{
			return ls.stream().map(e->e.getAge()).toList();
		}).orElseThrow();

		Optional<List<Integer>> optAges = Optional.of(lsAges);
		List<Integer> lsAgesNotNull = optAges.map(ls->{
			return ls;
		}).orElseThrow();

		IntSummaryStatistics intSummaryStatistics = lsAgesNotNull.stream().collect(Collectors.summarizingInt(i->i));

		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("Employee'sMaximumAge", intSummaryStatistics.getMax());
		map.put("Employee'sMinimumAge", intSummaryStatistics.getMin());
		map.put("Employee'sAverageAge", (int)intSummaryStatistics.getAverage());


		return map;
	}



	@GetMapping("api/test/unique/duplicate")
	public List<String> testtest(){
		Optional<List<Employee>> optEmployees = service.getAllEmployees();

		List<Employee> lsEmployees = optEmployees.map(e->e).orElseThrow();


		//		lsEmployees.stream().filter(e->e.getName()!=null).map(e->e.getName()).filter(name->Collections.frequency(lsEmployees, lsEmployees))



		List<String> list = lsEmployees.stream().filter(e->e.getName()!=null).map(e->e.getName()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream().filter(e->e.getValue()>1).map(e->e.getKey()).toList();


		return list;
	}

	@GetMapping("api/test/groupingby/age")
	public Map<Integer,List<Employee>> testtest2(){
		Optional<List<Employee>> optEmployees = service.getAllEmployees();

		List<Employee> lsEmployees = optEmployees.map(ls->ls).orElseThrow();


		//		lsEmployees.stream().map(emp->emp.getAge()).
		//		collect(Collectors.
		//				toMap(age->age, Collectors.counting(),(e1,e2)->e1,LinkedHashMap::new));
		//		


		//		Map<Integer,Integer> ageCounts = 
		//			    lsEmployees.stream()
		//			               .collect(Collectors.toMap(Employee::getAge, 
		//			                                         emp -> 1,
		//			                                         (v1,v2)->v1+v2));

		Map<Integer,List<Employee>> collect = lsEmployees.stream().collect(Collectors.groupingBy(e->e.getAge()));

		//Map<Integer,Set<Employee>> collect = lsEmployees.stream().collect(Collectors.groupingBy(e->e.getAge(),Collectors.toSet()));


		Map<Integer,List<Employee>> collect2 = lsEmployees.stream().collect(Collectors.groupingBy(e->e.getAge(),TreeMap::new,Collectors.toList()));

		return collect2;
	}

	@GetMapping("api/test/common/elements/array")
	public List<Integer> testtest3(){

		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {6,7,1,2,7};


		List<Integer> list = Arrays.stream(arr1).filter(i1->Arrays.stream(arr2).anyMatch(i2->i2==i1)).boxed().toList();




		return list;
	}	


	@GetMapping("api/test/reverse/array")
	public int[] testtest4(){

		int[] arr1 = {1,2,3,4,5};

		IntStream.range(0, arr1.length/2).forEach(i->{
			int lastIndex = arr1.length-1;
			int temp = arr1[i];
			arr1[i]=arr1[lastIndex-i];
			arr1[lastIndex-i] = temp;
		});

		return arr1;
	}	


}
