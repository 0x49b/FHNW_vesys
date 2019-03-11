package ch.fhnw.ds.jsonrpc;

import java.util.Date;

class ServiceImpl implements Service {

	@Override
	public String echo(String arg) {
		if (arg == null) {
			throw new NullPointerException();
		} else if (arg.contentEquals("ex")) {
			throw new IllegalArgumentException("ex");
		} else {
			return String.format("Echo: %s %s", arg, new Date());
		}
	}
}