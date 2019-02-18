package ch.fhnw.ds.networking.tcp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaces {

	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface intf = interfaces.nextElement();
			
			System.out.print(intf.getName());
			System.out.println(" ["+intf.getDisplayName()+"]");
			
			Enumeration<InetAddress> adr = intf.getInetAddresses();
			while(adr.hasMoreElements()){
				System.out.println("\t"+adr.nextElement());
			}

		    byte[] hardwareAddress = intf.getHardwareAddress();
		    if (hardwareAddress != null) {
				String mac = "";
				for (int i = 0; i < hardwareAddress.length; i++){
					mac += String.format((i == 0 ? "" : "-") + "%02X",
							hardwareAddress[i]);
				}
				System.out.println(mac);
			} else {
		    	System.out.println("no mac address");
		    }
		    
		    System.out.println();

//			for(InterfaceAddress address : intf.getInterfaceAddresses())
//				System.out.println("\tInterfaceAddress: "+address);
		}

	}

}
