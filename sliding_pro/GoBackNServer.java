import java.lang.System;
import java.net.*; 
import java.io.*;

import java.util.Random;


public class GoBackNServer 
{
    static ServerSocket Serversocket;
    
    public static void main(String[] args)  
    {

        try 
        {
            int v[] = new int[10]; 
            int n = 0;
            Random rands = new Random();
            int rand = 0; 
            Serversocket = new ServerSocket(8011);//8011 is port
            System.out.println("waiting for connection");
            Socket client = Serversocket.accept();
            System.out.println("Connection established.");
            DataOutputStream out = new DataOutputStream(
                    client.getOutputStream());
            DataInputStream in = new DataInputStream(
                    client.getInputStream());
            int p = in.read(); //reading number of frames
            System.out.println("No of frame is:" + p);

            for (int i = 0; i < p; i++) {
                v[i] = in.read(); // reading data 
                System.out.println(v[i]);
                
            }
            rand = rands.nextInt(p);   //random packet being generated or lost
            v[rand] = -1;
            for (int i = 0; i < p; i++)
             {
                    System.out.println("Received frame is: " + v[i]);

                }
            for (int i = 0; i < p; i++)
                if (v[i] == -1) {
                    System.out.println("Request to retransmit frames from"
                            + (i+1) + " again!");
                    n = i;
                    out.write(n); //sending which packet is lost
                    out.flush();
				}

			System.out.println();
			
			System.out.println("Frames after " +(n+1)+" retransmitted:\n");

				for(int j = n; j < p; j++){
					DataInputStream in1 = new DataInputStream(client.getInputStream());
					v[j] = in1.read(); 
					System.out.println(v[j]);
				}
            System.out.println("Exiting");
        } catch (Exception e) {
            System.out.println(e);
        }
            
    }
}