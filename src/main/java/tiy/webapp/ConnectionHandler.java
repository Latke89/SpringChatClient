package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Brett on 9/12/16.
 */
public class ConnectionHandler implements Runnable {

	Socket connection;
	ArrayList<String> historyList;

	public ConnectionHandler(Socket connection, ArrayList<String> historyList) {
		this.connection = connection;
		this.historyList = historyList;
	}

	public void run() {
		handleIncomingConnections(connection, historyList);
	}


	private void handleIncomingConnections(Socket inputSocket, ArrayList<String> list) {
		try {
//			System.out.println("Incoming connection from " + inputSocket.getInetAddress().getHostAddress());

			BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
			PrintWriter outputToClient = new PrintWriter(inputSocket.getOutputStream(), true);


			String inputLine = inputFromClient.readLine();
			if(inputLine.equals(":::START_HISTORY_TRANSFER:::")) {
				MessageContainer myContainer = new MessageContainer();
				for (String message : list) {
//					System.out.println(message);
					myContainer.messages.add(message);
					outputToClient.println(message);
				}
				outputToClient.println(":::END_HISTORY_TRANSFER:::");
			}else {

					System.out.println(inputLine);
					historyList.add(inputLine);
					outputToClient.println("roger roger");
				}

		}catch (IOException exception){
			exception.printStackTrace();
		}
	}
}
