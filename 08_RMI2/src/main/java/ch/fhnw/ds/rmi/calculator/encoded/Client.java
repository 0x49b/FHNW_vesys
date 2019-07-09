package ch.fhnw.ds.rmi.calculator.encoded;

// -Djava.security.policy=rmi/calculator/policy.txt
// java -Djava.security.policy=rmi\calculator\policy.txt rmi.calculator.Client 

import java.rmi.Naming;

import ch.fhnw.ds.rmi.calculator.Calculator;


public class Client {

    public static void main(String[] args) throws Exception {
     	System.setSecurityManager(new SecurityManager());

		String host = "localhost";
		if(args.length > 0){ host = args[0]; }

		System.out.println("connecting to rmi://"+host+"/CalculatorService");

		Calculator c = null;
		c = (Calculator)Naming.lookup("rmi://"+host+"/CalculatorService");
		
        System.out.println( "4+5=" + c.add(4, 5) );
    }
}



//			try{
//			java.util.Properties props = new java.util.Properties();
//			props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
//			props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
//			props.put(javax.naming.Context.PROVIDER_URL, "iiop://localhost:900");
//
//			// Create the initial context from the properties we just created
//			javax.naming.Context initialContext = new javax.naming.InitialContext(props);
//
//			//Calculator c = (Calculator)initialContext.lookup("rmi://" + host + "/CalculatorService");
//			c = (Calculator)initialContext.lookup("CalculatorService");
//			}
//			catch(javax.naming.NamingException e){
//			}

