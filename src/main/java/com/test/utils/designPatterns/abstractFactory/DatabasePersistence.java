package com.test.utils.designPatterns.abstractFactory;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
public class DatabasePersistence implements PersistenceType {

	@Override
	public String type() {
		return "database";
	}

}
