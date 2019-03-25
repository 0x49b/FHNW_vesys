package ch.fhnw.ds.rest.msg.server;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	@Override
	public String marshal(LocalDateTime value) throws Exception {
		return value.toString();
	}

	public LocalDateTime unmarshal(String value) throws Exception {
		return LocalDateTime.parse(value);
	}

}