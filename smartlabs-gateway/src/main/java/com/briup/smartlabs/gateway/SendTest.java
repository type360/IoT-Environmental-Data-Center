package com.briup.smartlabs.gateway;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class SendTest {
	public static void main(String[] args) {
		try(Socket socket  = new Socket("127.0.0.1", 9999);
				PrintStream ps = 
						new PrintStream(socket.getOutputStream());
				InputStream is = socket.getInputStream()){
				ps.println("23309418");
				ps.flush();
				Thread.sleep(1000);
				ps.println("2430A41A");
				ps.flush();
				Thread.sleep(1000);
				ps.println("2530341B");
				ps.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
