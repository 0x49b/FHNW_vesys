package ch.fhnw.ds.servlet.echo;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet(
	urlPatterns={"/echo2/*"},
	initParams={ 
	   @WebInitParam(name="title", value="Annotation based EchoServlet2") 
	}
)
public class EchoServlet2 extends EchoServlet { }
