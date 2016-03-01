package com.mastering.jms.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mastering.jms.queue.register.MobiGeneratorListenerRegister;

@WebServlet("/mobi-receiver")
public class MobiGeneratorListenerServlet extends HttpServlet {

	private static final long serialVersionUID = 329487669609761951L;

	@Inject
	private MobiGeneratorListenerRegister receiver;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Trying to receive a new Mobi message...");
		
		receiver.receive();
	}

}
