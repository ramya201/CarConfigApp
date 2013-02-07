package edu.cmu.java.Driver;

import java.net.*;
import edu.cmu.java.network.Client;

public class ClientTest {

	public static void main(String[] args) {
		try {
			Client client = new Client(InetAddress.getLocalHost(),6000);
			client.run();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}

	}

}
