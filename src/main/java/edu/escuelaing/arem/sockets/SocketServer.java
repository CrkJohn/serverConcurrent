package edu.escuelaing.arem.sockets;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class SocketServer{

    /**
     *  This method instance a ServerSocket on port, where the port can be a default port or a port 
     *  given enviorement variables
     *  @return ServerSocket serversocket  where the communication will begin
     */
	public static ServerSocket runServer() {        
		 try {
	            ServerSocket serverSocket = new ServerSocket(getPort());
	            System.out.println("Ready to listen 4567");
	            return serverSocket;
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: 4567.");
	            System.exit(1);
	            return null;
	       }
	}  
    /**
     *  This method search the port where will start el service
     *  @return  new port 
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}