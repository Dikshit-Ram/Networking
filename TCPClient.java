import java.io.*;
import java.net.*;

public class TCPClient {

	//Port number that server listens to
	private final static int  DEST_PORT = 5901;
	private final static String SERVER_NAME = new String("localhost");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			//Get IP address object of server from hostname
			
			InetAddress dstIP = InetAddress.getByName(SERVER_NAME);
			
			//Create local socket and connect to server
			Socket clientSocket = new Socket(dstIP, DEST_PORT);
			
			//Read text from Keyboard
			BufferedReader kbdReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter text in lower case:");
			
			String inputString = kbdReader.readLine();
				
			//Get the input and output streams of the socket
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//write the bytes out to the server
			out.println(inputString);
			
			//read reply from server as a byte stream
			String serverReply = in.readLine();			
			
			System.out.println("Server reply: " + new String(serverReply));
			
			//Close input/output streams and socket before exiting
			out.close();
			in.close();
			clientSocket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
