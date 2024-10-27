package com.test.utils.designPatterns.abstractFactory;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
public class RoleDeleteObject extends PersistenceRole {

	public RoleDeleteObject(PersistenceType persistenceType) {
		super(persistenceType);
		
	}

	@Override
	void defineRole(Object obj) {
		System.out.println("deleting Object with ID "+obj+" from "+persistenceType.type());
	}

}
