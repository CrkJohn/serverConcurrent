package edu.escuelaing.arem;

import edu.escuelaing.arem.threads.ClientHandler;
import edu.escuelaing.arem.threads.ThreadPool;

/**
 * This is the App Controller that initializes a server, and then the server
 * starts to listen on a port.
 */
public class Controler {
    public static void main(String[] args) throws Exception {
        ClientHandler.initiate(); 
        ThreadPool.start();   
    }
}
 