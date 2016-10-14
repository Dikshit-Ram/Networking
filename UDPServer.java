/**
 * 
 * Program: UDP server program receives lower case string from client.
 * 			Converts text to upper case and replies to client.
 * Parameters: None
 * Author: Sumesh J. Philip
 * Version: 1.0
 */
//package UDPApplication;

import java.io.*; 
import java.net.*; 

public class UDPServer {

	// port number to listen to
	private static final int DEST_PORT = 5901;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			//Create local port and bind to DEST_PORT
			DatagramSocket serverSocket = new DatagramSocket(DEST_PORT); 	  
			
			//Server always listens for incoming connections
			while(true) 
	        {
				byte[] receiveData = new byte[1024]; 
				
				//Create UDP packet object to receive packet from client
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
				System.out.println("Waiting for Client");
				
				// Blocking receive
				serverSocket.receive(receivePacket); 
				
				//Client packet contains source IP address and port number.
				//Get these values to reply back to client
				InetAddress clientIP = receivePacket.getAddress();
				int clientPort = receivePacket.getPort();
				
				//Get text from client and convert to upper case
				String recvString = new String(receivePacket.getData());
				recvString = recvString.toUpperCase();
				
				//Create reply packet to send back to client
				DatagramPacket sendPacket = new DatagramPacket(recvString.getBytes(), recvString.length(), clientIP, clientPort);
				
				//Send packet to client
				serverSocket.send(sendPacket);
				System.out.println("Finished processing");
	        }              
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
