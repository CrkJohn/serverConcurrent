package edu.escuelaing.arem.htmlhandler;

public interface Handler{
	
	/**
	 * 	It executes a method
	 * @return The string value returned by the executed method
	 * @throws Exception
	 */
    public String process() throws Exception;
   
    
    /**
	 * 	It executes a method
	 * @param params An array with parameters that will receive the method to be invoked
	 * @return The string value returned by the executed method
	 * @throws Exception
	 */
    public  String process(Object[] arg)  throws Exception;
}