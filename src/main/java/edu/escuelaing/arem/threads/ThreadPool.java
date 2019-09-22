package edu.escuelaing.arem.threads;

import edu.escuelaing.arem.sockets.SocketClient;
import edu.escuelaing.arem.sockets.SocketServer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

	 public static ServerSocket serverSocket = SocketServer.runServer();
	 public static ExecutorService exService = Executors.newCachedThreadPool();
	        

	 public static void start() {
		 while (true) {
	            Socket clientSocket = SocketClient.receiveRequest(serverSocket);
	            exService.execute(new ClientHandler(clientSocket));

	     }
	 }

}