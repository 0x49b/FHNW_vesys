/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package ch.fhnw.ds.servlet.currency;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	name="CurrencyConverter", 
	urlPatterns={"/currency/convert"}
)
public class Converter extends HttpServlet {
	private static final long serialVersionUID = -2045238023217069192L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String amount = request.getParameter("amt");
		String from = request.getParameter("from");
		String to = request.getParameter("to");

		String res = computeResult(amount, from, to);

		out.println("<html>");
		out.println("<head><title>Currency Converter</title></head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h1>Currency Converter</h1>");
		out.printf("%s %s = %s %s\n", amount, from, res, to);
		out.printf("</br><a href=\"%s/currency\">Curency Converter</a>\n", request.getContextPath());
		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

	static String computeResult(String amount, String from, String to){
		String TOKEN = "green";
		try {
			String query = "https://www.calculator.net/currency-calculator.html?eamount="+amount+"&efrom="+from+"&eto="+to+"&x=5";
			System.out.println(query);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(query)).GET().build();
			HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());
			return response.body()
					.filter(line -> line.contains(TOKEN))
					.findFirst()
					.map(line -> {
						int pos = line.indexOf(TOKEN, 0);
						pos = line.indexOf("<b>", pos);
						String res = line.substring(pos+3);
						return res.substring(0, res.indexOf("<"));
			}).orElse("no result found");
		} catch (Exception e) {
			String msg = e.getMessage();
			return "".equals(msg) ? e.toString() : msg;
		}
	}

}
