package org.core;

public aspect Aspect {
	pointcut pre(String name) : call( String HelloWorld.getHelloWorld(String)) && args(name);
	String around(String name) : pre(name) {
		String v = proceed("pre " + name);
		return v;
	}
}

