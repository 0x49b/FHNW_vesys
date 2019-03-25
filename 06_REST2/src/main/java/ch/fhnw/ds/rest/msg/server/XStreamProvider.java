package ch.fhnw.ds.rest.msg.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.thoughtworks.xstream.XStream;

@Provider
@Consumes("application/xstream")
@Produces("application/xstream")
public class XStreamProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
	private XStream xstream = new XStream();
	
	{
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(new Class[] { Msg.class });
//		xstream.allowTypesByWildcard(new String[] {
//			    "ch.fhnw.ds.rest.msg.server.**"
//		});
	}	

	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mimeType) {
		return true;
	}

	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mimeType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
		return xstream.fromXML(entityStream);
	}

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] ann, MediaType mimeType) {
		return true;
	}

	public long getSize(Object object, Class<?> type, Type genericType,
			Annotation[] ann, MediaType mimeType) {
		return -1;// size not yet known
	}

	public void writeTo(Object object, Class<?> type, Type genericType,
			Annotation[] ann, MediaType mimeType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) {
		xstream.toXML(object, entityStream);
	}
}
