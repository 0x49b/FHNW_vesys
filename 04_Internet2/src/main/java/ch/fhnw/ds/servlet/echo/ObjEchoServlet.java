/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.servlet.echo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/obj")
public class ObjEchoServlet extends HttpServlet {
	private static final long serialVersionUID = -3358125295412999263L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		
		try {
			Object x = in.readObject();
			System.out.println("doPost method: " + x);
			out.writeObject(x);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
}
