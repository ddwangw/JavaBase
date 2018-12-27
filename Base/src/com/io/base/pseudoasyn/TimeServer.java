package com.io.base.pseudoasyn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
	public static void main(String[] args) throws IOException{
		int port = 8080;
		if(args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		ServerSocket server = null ;
		try {
			server = new ServerSocket(port);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(5,10000);
			while(true) {
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		}finally {
			if(server != null) {
				server.close();
				server = null;
			}
		}
		
	}
}
