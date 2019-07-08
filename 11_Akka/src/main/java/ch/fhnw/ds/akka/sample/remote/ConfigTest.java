package ch.fhnw.ds.akka.sample.remote;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigTest {
	public static void main(String[] args) throws Exception {
		Config c = ConfigFactory.load().getConfig("PrintConfig");
		System.out.println(c.getObject("akka.actor"));
		System.out.println(c.getObject("akka.remote.netty"));
		System.out.println(c.getInt("akka.remote.netty.tcp.port"));
	}
}
