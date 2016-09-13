package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Brett on 9/12/16.
 */
public class ConnectionHandler implements Runnable {

	Socket connection;

	public ConnectionHandler(Socket incomingConnection) {
		this.connection = incomingConnection;
	}

	public void run() {
		handleIncomingConnections(connection);
	}


	private void handleIncomingConnections(Socket inputSocket) {
		try {
			System.out.println("Incoming connection from " + inputSocket.getInetAddress().getHostAddress());

			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
			PrintWriter outputToClient = new PrintWriter(inputSocket.getOutputStream(), true);


			String inputLine;
			while ((inputLine = inputFromClient.readLine()) != null) {
				System.out.println(inputLine);
				outputToClient.println("roger roger");
			}
		}catch (IOException exception){
			exception.printStackTrace();
		}
	}
}
