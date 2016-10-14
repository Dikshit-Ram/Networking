/**
 * 
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author sjp111
 *
 */
public class HttpServer {

	//Port number that server listens to
	//private final static int  DEST_PORT = 5901;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
			//Create server socket
			int serverPort = Integer.parseInt(args[0]);
			ServerSocket svrSocket = new ServerSocket(serverPort);

			while(true)
			{
				//Accept client request, this returns a local Socket
				//to communicate with the client
				Socket clientSocket = svrSocket.accept();

				//InetAddress clientIP = clientSocket.getInetAddress();
				//System.out.println("Connected to " + clientIP.toString());

				//Get the input and output streams of the socket
				//PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				try{

				String str = in.readLine();
				int i =0;
				while(str.length() !=0)
				{
					//for first line split request method, url and version and for remaining lines give name as header
					if(i ==0)
					{	
						String [] line1 = str.split(" ");
						System.out.println("Request Type : "+line1[0]	);
						System.out.println("URL	Requested: "+line1[1]);
						System.out.println("HTTP Version : "+line1[2]);
											}
					else if((str = in.readLine()).length()!=0)
					{
						//str = in.readLine();
						System.out.println("Header"+i+": "+str);
						
					}
					i++;
				}
				}
				catch (NullPointerException e)
				{
					System.out.println("");
				}
	
				//close input stream
				in.close();
				out.close();
				
			}
		}
		catch (Exception e )
		{
			e.printStackTrace();
		}
	}
}