package ch.fhnw.ds.internet;

import static java.lang.System.out;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLGetConnection {
	
	public static void main(String[] args) throws Exception{
		URL url = new URL("http://www.fhnw.ch");
		//URL url = new URL("http://localhost:8080/index.html");
		//URL url = new URL("http://web.fhnw.ch/business/projects/best/portlets/pics/logo_fhnw.gif");
		//URL url = new URL("http://www.nch.com.au/acm/11k16bitpcm.wav");
		URLConnection c = url.openConnection();
		
		out.println( "Date :           " + new Date(c.getDate()) );
		out.println( "Last Modified :  " + new Date(c.getLastModified()) );
		out.println( "Content type :   " + c.getContentType() );
		out.println( "Content length : " + c.getContentLength() ); 
		
		Object res = c.getContent();
		out.println( "Content:         " + res);
	}
	
}

/*

Date :           Sun Mar 05 16:29:16 CET 2017
Last Modified :  Tue Nov 29 07:33:07 CET 2011
Content type :   text/html
Content length : 1963
Content:         sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@1f32e575

Date :           Sun Mar 05 16:31:07 CET 2017
Last Modified :  Fri Mar 23 11:14:45 CET 2012
Content type :   image/gif
Content length : 2632
Content:         sun.awt.image.URLImageSource@3d4eac69

Date :           Sun Mar 05 16:31:29 CET 2017
Last Modified :  Sat Feb 25 03:01:37 CET 2012
Content type :   audio/x-wav
Content length : 304578
Content:         sun.applet.AppletAudioClip@1b2c6ec2

*/
