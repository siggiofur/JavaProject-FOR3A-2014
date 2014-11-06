package BattleShip;

import java.net.*; 
import java.io.*; 

public class chatServer extends Thread
{ 
	protected Socket clientSocket;
	public static void main(String[] args) throws IOException 
    	{ 
			ServerSocket serverSocket = null; 
			try { 
				serverSocket = new ServerSocket(10008); 
				System.out.println ("Connection Socket Created");
				try { 
					while (true)
					{
						System.out.println ("Waiting for Connection");
						new chatServer (serverSocket.accept()); 
					}
				} 
				catch (IOException e) 
				{ 
					
				} 
			} 
			catch (IOException e) 
			{  
				
			} 
			finally
			{
				try {
					serverSocket.close(); 
				}
				catch (IOException e)
				{ 
					
				} 
			}
    	}
		private chatServer (Socket clientSoc)
		{
			clientSocket = clientSoc;
			start();
		}
		public void run()
		{
			System.out.println ("Client connected");
			try { 
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
				BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream())); 
				String inputLine; 
				while ((inputLine = in.readLine()) != null) 
				{ 
					System.out.println ("Server: " + inputLine); 
					out.println(inputLine); 

					if (inputLine.equals("bye")) 
						break; 
				} 
				out.close(); 
				in.close(); 
				clientSocket.close(); 
			} 
			catch (IOException e) 
			{ 
				
			} 
		}
} 
