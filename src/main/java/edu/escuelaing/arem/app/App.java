package edu.escuelaing.arem.app;

import edu.escuelaing.arem.annotation.Web;



public class App{

	/**
	 * 
	 * 
	 * @return return a string with struct of a document html	
	 */
    @Web("greeting")
    public static String greeting(){
        return "<!DOCTYPE html>\n" + 
        		"<html>\n" + 
        		"<head>\n" + 
        		"  <meta charset=\"utf-8\" />\n" + 
        		"  <title>ARSW - Laboratorio 1</title>  \n" + 
        		"</head>\n" + 
        		"<body style=\"text-align: center\">   \n" + 
        		"    <h1>Hello world! ,  the POJOs works</h1>	\n" + 
        		"</body>\n" + 
        		"</html>\n" + 
        		"";
    }
    
    /**
     * 
     * @param name is a name where will be used for return string with greeting 
     * @return a string with struct of a document html greeting a name
     */
    
    @Web("greetingparam")
    public static String greeting(String name) {
        return "<!DOCTYPE html>\n" + 
        		"<html>\n" + 
        		"<head>\n" + 
        		"  <meta charset=\"utf-8\" />\n" + 
        		"  <title>ARSW - Laboratorio 1</title>  \n" + 
        		"</head>\n" + 
        		"<body style=\"text-align: center\">   \n" + 
        		"    <h1>Hello "+name+"! ,  the POJOs works</h1>	\n" + 
        		"</body>\n" + 
        		"</html>\n" + 
        		"";
    }
    
    
    @Web("greetingparam2")
    public static String greeting(String name , String ape) {
        return name + " " + ape;
    }
    
   
}
