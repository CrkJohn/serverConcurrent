package edu.escuelaing.arem.threads;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.net.Socket;
import javax.imageio.ImageIO;
import org.reflections.Reflections;
import java.awt.image.BufferedImage;

import edu.escuelaing.arem.annotation.Web;
import edu.escuelaing.arem.htmlhandler.Handler;
import edu.escuelaing.arem.htmlhandler.StaticMethodHandler;

import org.reflections.scanners.SubTypesScanner;

public class ClientHandler implements Runnable {

    private  Socket clientSocket;
    private static HashMap<String, Handler> hashMap = new HashMap<String, Handler>();
    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        
    }

     /**
     * Este metodo escucha y maneja las peticiones que le llegan al servidor web
     * 
     * @throws IOException
     */
    public void run() {
        try {
        	System.out.println("closed  " + clientSocket.isClosed());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine = null ; String requestUrl = null ;
            while ((inputLine = in.readLine()) != null) {
    			System.out.println("Received: " + inputLine);
    			if (!in.ready()) {
    				break;
    			}
    			if(inputLine.contains("GET")){
    				requestUrl = inputLine.split(" ")[1];
    				System.out.println("Adress to show: "+ requestUrl);
    			}
    		}
            notFound(out);
            /*
            
           
            
            
            if (requestUrl.equals("/")) {
                readHtml("/index.html",out);
            } else if (requestUrl.contains("/apps")) {
                readApps(requestUrl,out);
            } else if (requestUrl.contains("html")) {
                readHtml(requestUrl,out);
            } else if (requestUrl.contains("jpg")) {
                readJpg(requestUrl,out, clientSocket.getOutputStream());
            } else {
                notFound(out);
            }
          
            
              */
            out.close();
            try {
                clientSocket.close();
            }catch(IOException ioe) {
                System.out.println("Error closing client connection");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method load all methods that contains the label @web mading a framework
     * IoC
     */
    public static void initiate() {
        try {

            Reflections reflections = new Reflections("edu.escuelaing.arem.app", new SubTypesScanner(false));
            Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
            for (Class cls : allClasses) {
                for (Method m : cls.getMethods()) {
                    if (m.isAnnotationPresent(Web.class)) {
                        Class[] params = m.getParameterTypes();
                        hashMap.put("/apps/" + m.getAnnotation(Web.class).value(),
                                new StaticMethodHandler(cls.getDeclaredMethod(m.getName(), params)));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
 
    private static Object[] extractParams(String pet) throws IndexOutOfBoundsException {
        Object[] params = null;
        String[] preParams = pet.split("&");
        params = new Object[preParams.length];
        for (int i = 0; i < preParams.length; i++) {
            System.out.println("kjikikiikik you love - >>> " + preParams[i].split("=")[1]);
            params[i] = preParams[i].split("=")[1];
        }
        return params;
    }

    /**
     * This method sends the requested class to the browser.
     * 
     * @param inputLine this is the class' address that the browser wants.
     * @throws IOException
     */
    private static void readApps(String inputLine, PrintWriter out) throws IOException {
       
        try {
        	 int idApps = inputLine.indexOf("/apps");
             String subStrg = "";
             System.out.println(inputLine + "  line ");
             for (int ji = idApps; ji < inputLine.length() && inputLine.charAt(ji) != ' '; ++ji) {
                 subStrg += inputLine.charAt(ji);
             }
            out.write("HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n");
            if (subStrg.contains("?")) {
                int id = subStrg.indexOf('?');
                String newStre = subStrg.substring(id + 1);
                Object[] tmp = extractParams(newStre);
                System.out.println(Arrays.toString(tmp));
                int di2 = subStrg.indexOf("?");
                out.write(hashMap.get(subStrg.substring(0, di2)).process(tmp));
            } else {
                out.write(hashMap.get(subStrg).process());
            }
        } catch (Exception e) {
            e.printStackTrace();
            //notFound(out);
        }
      
    }

    /**
     * This method sends the requested html file to the browser.
     * 
     * @param inputLine this is the file with html extension that the browser wants.
     * @throws IOException
     */
    private static void readHtml(String inputLine , PrintWriter out) throws IOException {
       
        try {
        	 int i = inputLine.indexOf('/') + 1;
             String urlInputLine = "";
             while (!urlInputLine.endsWith(".html") && i < inputLine.length()) {
                 urlInputLine += (inputLine.charAt(i++));
             }
            BufferedReader rd = new BufferedReader(
                    new FileReader(classLoader.getResource(urlInputLine).getFile()));
            StringBuffer sb = new StringBuffer();
            String infile = null;
            while ((infile = rd.readLine()) != null) {
                sb.append(infile);
            }
            out.println("HTTP/1.1 200 OK\r");
            out.println("Content-Type: text/html\r");
            out.println("\r");
            out.println(sb.toString());
        } catch (Exception e) {
            //notFound(out);
        }
    }

    /**
     * This method sends the requested image to the browser.
     * 
     * @param inputLine    this is the image with jpg extension that the browser
     *                     wants.
     * @param clientSocket this is the brower's socket
     * @throws IOException
     */
    private static void readJpg(String inputLine , PrintWriter out,  OutputStream outStream) throws IOException {
       
        try {
        	 String urlInputLine = "";
             int i = (inputLine.contains("=")) ? inputLine.indexOf("=") + 1 : inputLine.indexOf('/') + 1;
             while (!urlInputLine.endsWith(".jpg") && i < inputLine.length()) {
                 urlInputLine += (inputLine.charAt(i++));
             }
            File image = new File(classLoader.getResource(urlInputLine).getFile());
            BufferedImage bImage = ImageIO.read(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] github = bos.toByteArray();
            DataOutputStream outImg = new DataOutputStream(outStream);
            outImg.writeBytes("HTTP/1.1 200 OK \r\n");
            outImg.writeBytes("Content-Type: image/jpg\r\n");
            outImg.writeBytes("Content-Length: " + github.length);
            outImg.writeBytes("\r\n\r\n");
            outImg.write(github);
            outImg.close();
            out.println(outImg.toString());
        } catch (Exception e) {
            //notFound(out);
        }
    }

    /**
     * This method send an 404 (not found)
     * 
     * @throws IOException
     */
    private static void notFound(PrintWriter out) {
        try {
            BufferedReader rd = new BufferedReader(
                    new FileReader(classLoader.getResource("notFound.html").getFile()));
            StringBuffer sb = new StringBuffer();
            String infile = null;
            while ((infile = rd.readLine()) != null) {
                sb.append(infile);
            }
            out.println("HTTP/1.1 200 OK\r");
            out.println("Content-Type: text/html\r");
            out.println("\r");
            out.println(sb.toString());  
        } catch (IOException ex) {
            System.err.println("Err:");
        }
    }

}