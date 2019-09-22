package edu.escuelaing.arem.htmlhandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler{

	private Method method; 

	public  String process() throws Exception {
		return (String) method.invoke(method, null);
	}

	public  String process(Object[] arg) throws Exception {
		return (String) method.invoke(method, arg);
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public StaticMethodHandler(Method method) {
		this.method = method;
	}


}