package ch.fhnw.ds.rest.msg.server;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Msg {
	private String text;
	private LocalDateTime date;

	public Msg() {
	}

	public Msg(String text) {
		this(text, LocalDateTime.now());
	}

	public Msg(String text, LocalDateTime date) {
		this.text = text;
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
