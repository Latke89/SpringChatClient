package tiy.webapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Brett on 9/12/16.
 */
public class Server implements Runnable{

	public void run() {

	}

	public static void main(String[] args) {
		Server myServer = new Server();
		myServer.startServer();
	}


	public void startServer() {
		try {
			ServerSocket serverListener = new ServerSocket(8005);
			System.out.println("Ready to accept incoming connections!");
			ArrayList<String> messsageHistory = new ArrayList<>();

			while(true){
				Socket clientSocket = serverListener.accept();
				ConnectionHandler myHandler = new ConnectionHandler(clientSocket, messsageHistory);
				Thread myThread = new Thread(myHandler);
				myThread.start();
			}



		}catch(IOException serverException){
			serverException.printStackTrace();
		}

	}

}