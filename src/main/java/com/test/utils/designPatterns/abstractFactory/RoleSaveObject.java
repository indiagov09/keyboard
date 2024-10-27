package com.test.utils.designPatterns.abstractFactory;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
public class RoleSaveObject extends PersistenceRole {

	public RoleSaveObject(PersistenceType persistenceType) {
		super(persistenceType);
		
	}

	@Override
	void defineRole(Object obj) {
		System.out.println(obj+" is being saved in "+persistenceType.type());
	}

}
