package edu.escuelaing.arem.threads;

import edu.escuelaing.arem.sockets.SocketClient;
import edu.escuelaing.arem.sockets.SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

	 private static ExecutorService executorService = Executors.newCachedThreadPool();        	        
	 private static ServerSocket serverSocket;
	 
	 public static void start() {
		 try {
			 serverSocket = SocketServer.runServer();
			 while (true) {
				 try {
				  Socket s = serverSocket.accept();
				  executorService.execute(new ClientHandler(s));
				 } catch(IOException ioe) {
	                 System.out.println("Error accepting connection");
	                 ioe.printStackTrace();
	             }
		     }
		 }catch (Exception e) {
			// TODO: handle exception
		 } 
	 }

}