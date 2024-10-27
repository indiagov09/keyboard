package com.test.utils.designPatterns.abstractFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.*;


import java.util.function.*;
public class MainClass {

	public static void main(String[] args) {
	
	PersistenceRole role = new RoleDeleteObject(new DatabasePersistence());
	role.defineRole(1);
	}

}



class Singleton implements Serializable{
	/*
	 */
	private static final long serialVersionUID = -4370395582299130189L;
	static Singleton singleton = null;
	
	private Singleton() {
		if(singleton!=null) {
			throw new RuntimeException("reflection not allowed");
		}
	}
	
	public static Singleton getInstance() {
		if(singleton==null) {
			synchronized (Singleton.class) {
				if(singleton==null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	
	public Object readResolve() {
		return singleton;
	}
	
	
}