package edu.cmu.java.network;

import java.io.*;
import java.net.*;

public class Server {
	private ServerSocket serverSock;

	public Server(int portNo) {
		try {
			serverSock = new ServerSocket(portNo);
			System.out.println("Server is listening on Port " + portNo);
		} catch (IOException e) {
			System.err.println("Could not listen on Port " + portNo);
		}
	}

	public ServerSocket getServerSock() {
		return serverSock;
	}
	
}
