package ch.fhnw.ds.internet.server.jdk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpDemoServer {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
		server.createContext("/date", new DateHandler());
		server.createContext("/bank", new BankHandler()).getFilters().add(new ParameterParser());
		server.createContext("/echo", new EchoHandler()).getFilters().add(new ParameterParser());
		
		server.start();
		System.out.println("server started on " + server.getAddress());
	}

	static class DateHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.getResponseHeaders().add("Content-type", "text/html");
			String response = "<b style='font-family:Arial'>" + new Date() + "</b> for " + exchange.getRequestURI();
			exchange.sendResponseHeaders(200, response.length());
			// exchange.sendResponseHeaders(200, 0);
			try(OutputStream os = exchange.getResponseBody()) {
				os.write(response.getBytes());
			}
		}
	}
	
	static class EchoHandler implements HttpHandler {
		@SuppressWarnings("unchecked")
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Writer w = new BufferedWriter(new OutputStreamWriter(bos));

			w.append(httpExchange.getRequestMethod() + " ");
			w.append(httpExchange.getRequestURI() + "\n\n");

			for (Entry<String, List<String>> e : httpExchange.getRequestHeaders().entrySet()) {
				w.append(e.getKey() + ": ");
				Iterator<String> it = e.getValue().iterator();
				while (it.hasNext()) {
					w.append(it.next());
					if (it.hasNext()) w.append(", ");
				}
				w.append("\n");
			}
			w.append("\n");
			
			Map<String, Object> params = (Map<String, Object>) httpExchange.getAttribute("parameters");
			if (params != null) {
				for (Entry<String, Object> e : params.entrySet()) {
					w.append(e.getKey() + " = " + e.getValue() + "\n");
				}
			}
			
			w.flush();
			w.close();
			
			byte[] buf = bos.toByteArray();
			httpExchange.getResponseHeaders().add("Content-type", "text/plain");
			httpExchange.sendResponseHeaders(200, buf.length);
			OutputStream os = httpExchange.getResponseBody();
			os.write(buf);
			os.close();
		}
	}

	static class BankHandler implements HttpHandler {
		// Stores the balance for each user
		private final Map<String, Double> users = new HashMap<>();

		@SuppressWarnings("unchecked")
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String response = "";
			if(exchange.getRequestURI().getPath().endsWith("register")) {
				StringBuilder buf = new StringBuilder();
				buf.append("<HTML><head><style>html, body { font-family: 'Arial', sans-serif;}</style></head><BODY><H1>Create Account</H1>");
				buf.append("<form name=\"register\" action=\"/bank\" method=POST>");
				buf.append("<TABLE>");
				buf.append("<TR><TD>Name:</TD> <TD><input size=40 maxlength=40 name=\"user\"></TD></TR>");
				buf.append("<TR><TD>Amount:</TD><TD><input size=40 maxlength=40 type=\"number\" step=0.01 name=\"amount\"></TD></TR>");
				buf.append("</TABLE>");
				buf.append("<p>");
				buf.append("<input name=\"submit\" type=submit value=\"Absenden\">");
				buf.append("</form>");
				buf.append("</body></html>");
				response = buf.toString();
			} else if ("GET".equals(exchange.getRequestMethod())) {
				StringBuilder buf = new StringBuilder();
				buf.append("<HTML><head><style>html, body { font-family: 'Arial', sans-serif;} table {border-collapse: collapse;}</style></head><BODY><H1>Accounts</H1>");
				buf.append("<table border=1>");
				for(String name: users.keySet()) {
					buf.append(String.format("<tr><td width=300>%s</td><td width=100 align=right>%20.2f</td></tr>", name, users.get(name)));
				}
				buf.append("</table>");
				buf.append("<a href=\"bank/register\">create new account</a>");
				buf.append("</body></html>");
				response = buf.toString();
			} else if ("POST".equals(exchange.getRequestMethod())) {
				Map<String, Object> params = (Map<String, Object>) exchange.getAttribute("parameters");
				String user = (String)params.get("user");
				double amount = Double.parseDouble((String)params.get("amount"));
				users.put(user, amount);
				exchange.getResponseHeaders().add("Location", "/bank");
				exchange.sendResponseHeaders(301, -1);
				return;
			}
			exchange.getResponseHeaders().add("Content-type", "text/html; charset=UTF-8");
			exchange.sendResponseHeaders(200,0);
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes(Charset.forName("UTF-8")));
			os.close();
		}
		
	}

	static class ParameterParser extends Filter {

		@Override
		public String description() {
			return "Parses the requested URI for parameters";
		}

		@Override
		public void doFilter(HttpExchange exchange, Chain chain)
				throws IOException {
			parseGetParameters(exchange);
			parsePostParameters(exchange);
			chain.doFilter(exchange);
		}

		private void parseGetParameters(HttpExchange exchange)
				throws UnsupportedEncodingException {
			Map<String, Object> parameters = new HashMap<>();
			URI requestedUri = exchange.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			exchange.setAttribute("parameters", parameters);
		}

		private void parsePostParameters(HttpExchange exchange)
				throws IOException {
			if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
				@SuppressWarnings("unchecked")
				Map<String, Object> parameters = (Map<String, Object>) exchange
						.getAttribute("parameters");
				InputStreamReader isr = new InputStreamReader(
						exchange.getRequestBody(), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String query = br.readLine();
				parseQuery(query, parameters);
			}
		}

		@SuppressWarnings("unchecked")
		public static void parseQuery(String query,
				Map<String, Object> parameters)
				throws UnsupportedEncodingException {
			if (query != null) {
				StringTokenizer st = new StringTokenizer(query, "&");
				while (st.hasMoreTokens()) {
					String keyValue = st.nextToken();
					StringTokenizer st2 = new StringTokenizer(keyValue, "=");
					String key = null;
					String value = "";
					if (st2.hasMoreTokens()) {
						key = st2.nextToken();
						key = URLDecoder.decode(key, "UTF-8");
					}

					if (st2.hasMoreTokens()) {
						value = st2.nextToken();
						value = URLDecoder.decode(value, "UTF-8");
					}

					if (parameters.containsKey(key)) {
						Object o = parameters.get(key);
						if (o instanceof List) {
							List<String> values = (List<String>) o;
							values.add(value);
						} else if (o instanceof String) {
							List<String> values = new ArrayList<String>();
							values.add((String) o);
							values.add(value);
							parameters.put(key, values);
						}
					} else {
						parameters.put(key, value);
					}
				}
			}
		}
	}
}
