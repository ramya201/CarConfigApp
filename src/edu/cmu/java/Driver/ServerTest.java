package edu.cmu.java.Driver;

import java.io.*;
import edu.cmu.java.network.*;

public class ServerTest {

	public static void main(String[] args) {
		Server server = new Server(6000);

		while (true) {
			try {				
				Thread proxyThread = new Thread(new ProxyServer(server.getServerSock().accept()));				
				proxyThread.start();
				System.out.println("Server continues listening for new connections\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
