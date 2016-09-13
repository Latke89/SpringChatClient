package tiy.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Brett on 9/12/16.
 */
public class WebChatClient {

	String serverResponse;

	public String sendMessage(String message) {

			try {
				Socket clientSocket = new Socket("localhost", 8005);

				PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				outputToServer.println(message);
				serverResponse = inputFromServer.readLine();
				System.out.println(serverResponse);

				clientSocket.close();


			} catch (IOException clientException) {
				clientException.printStackTrace();
			}
		return serverResponse;
	}

}
