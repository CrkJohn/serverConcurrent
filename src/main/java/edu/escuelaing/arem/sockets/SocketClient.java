package edu.escuelaing.arem.sockets;

import java.io.IOException;
import java.net.*;


/**
 * This is the client(browser) class which has a socket as attribute.
 * 
 */
public class SocketClient {
		
	/**
	 *  this method listens for a connection to be made to this socket and accepts it. T
	 *  he method blocks until a connection is made.
	 *  @param  serverSocket socket to help for communication between two services client-service.
	 *  @return Socket socket new socket
	 */
	 public static Socket receiveRequest(ServerSocket serverSocket) {
		    try {
	            Socket clientSocket = serverSocket.accept();
	            System.out.println("Readyyy ...");
	            return clientSocket;
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	            System.exit(1);
	            return null;
	        }
	}
}
