package com.mastering.jms.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mastering.jms.queue.producer.MobiGeneratorQueueProducer;

@WebServlet("/mobi-generator")
public class MobiGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = 8542397768904506933L;
	
	@Inject
	private MobiGeneratorQueueProducer sender;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Trying to send message..");
		
		sender.send();
	}
}
