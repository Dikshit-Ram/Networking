/**
 * 
 */

import java.io.*;
import java.net.*;

/**
 * @author sjp111
 *
 */
public class TCPServer {

	//Port number that server listens to
	private final static int  DEST_PORT = 5901;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			//Create server socket
			ServerSocket svrSocket = new ServerSocket(DEST_PORT);
			
			while(true)
			{
				//Accept client request, this returns a local Socket
				//to communicate with the client
				Socket clientSocket = svrSocket.accept();
			
				InetAddress clientIP = clientSocket.getInetAddress();
				System.out.println("Connected to " + clientIP.toString());
				
				//Get the input and output streams of the socket
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				//read the bytes from the input stream
				String str = in.readLine();
				
				//convert text to upper case
				str = str.toUpperCase();
				
				//write bytes to client
				out.println(str);
				
				//close streams. Client will close local socket
				in.close();
				out.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
