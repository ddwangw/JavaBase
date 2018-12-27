package com.io.base.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		if(args != null && args.length >0) {
			try {
				port = Integer.parseInt(args[0]);
			}catch(NumberFormatException e) {
				
			}
		}
		
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket("127.0.0.1",port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			String resp = in.readLine();
			System.out.println("Now is :"+resp);
		}catch(Exception e) {
			
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch(IOException el) {
					el.printStackTrace();
				}
			}
			
			if(out != null) {
				out.close();
				out = null;
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				socket = null;
			}
		}

	}

}
