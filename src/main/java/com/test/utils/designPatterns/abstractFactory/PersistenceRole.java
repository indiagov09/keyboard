package com.test.utils.designPatterns.abstractFactory;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
public abstract class PersistenceRole {
PersistenceType persistenceType;

public PersistenceRole(PersistenceType persistenceType) {
	this.persistenceType = persistenceType;
}



abstract void defineRole(Object obj);

}
