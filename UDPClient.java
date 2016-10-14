/**
 * 
 * Program: UDP client program sends a lower case string to server.
 * 			Prints server reply and exits
 * Parameters: None
 * Author: Sumesh J. Philip
 * Version: 1.0
 */

//package UDPApplication;


import java.io.*;
import java.net.*;

public class UDPClient {

	// port number to connect
	private static final int DEST_PORT = 5910;
	private static final String SERVER_NAME = "toolman.wiu.edu";
	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		String inputString;

		try
		{
			// Create BufferedReader to read line from Keyboard
			BufferedReader kbdReader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter text in lower case:");
			
			inputString = kbdReader.readLine();
			//System.out.println(inputString);
			
			byte[] sendBytes = inputString.getBytes();
	
			//Create IP address object from IP address of destination 
			
			InetAddress dstIP;
			dstIP = InetAddress.getByName(SERVER_NAME);
			
			// Create  UDP socket and UDP packet. Since there is no
			// connection between the devices, the destination address
			// and port number must be specified in the packet.
			DatagramPacket sendPkt = new DatagramPacket(sendBytes, inputString.length(), dstIP, DEST_PORT);
			DatagramSocket clientSocket = new DatagramSocket();
			
			//Send packet to server 
			clientSocket.send(sendPkt);
			
			byte[] receiveData = new byte[1024];
			
			//DatagramPacket object must be created for receiving data from server
			DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
			
			//Receive reply from server
			clientSocket.receive(recvPacket);
			
			String recvString = new String(recvPacket.getData(), 0, recvPacket.getLength());
			System.out.println("Received text " + recvString + " from Server");
			
			//Close socket before exiting
			clientSocket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
