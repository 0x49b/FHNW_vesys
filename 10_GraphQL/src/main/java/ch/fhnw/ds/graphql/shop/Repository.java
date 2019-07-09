package ch.fhnw.ds.graphql.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Repository {
	
	private static Map<String, Customer> customers = new HashMap<>();

	private static Map<String, Product> products = new HashMap<>();
	
	static Map<Customer, List<Rating>> cRatings = new HashMap<>();
	static Map<Product, List<Rating>> pRatings = new HashMap<>();

	static {
		customers.put("1", new Customer("1", "Dominik"));
		customers.put("2", new Customer("2", "Wolfgang"));
		customers.put("3", new Customer("3", "Daniel"));

		products.put("1", new Product("1", "PHILIPPI Paco Brieföffner", "Wenn der Postmann zweimal klingelt", "https://static.digitecgalaxus.ch/Files/1/3/1/9/0/2/1/2/Unbenannt-121.jpeg"));
		products.put("2", new Product("2", "Driade Nemo", "Kunst- und Sitzobjekt zugleich", "https://static.digitecgalaxus.ch/Files/8/1/4/9/5/6/2/051002079_01_1.jpg"));
		products.put("3", new Product("3", "Monkey Business Umbrella", "Das etwas andere Teesieb.", "https://static.digitecgalaxus.ch/Files/5/1/7/0/5/5/7/ot800_teeei_regenschirm4_k_z1.jpg"));
		
		rateProduct("1", "1", 1, "wirklich schlecht");
		rateProduct("2", "1", 2, "na ja");
		rateProduct("2", "2", 2, "super produkt");
		rateProduct("3", "1", 4, "find ich toll");
		rateProduct("3", "2", 3, "was soll das genau sein");
		rateProduct("3", "3", 3, "hervorragend");
	}

	public static Optional<Customer> getCustomerById(String customerId) {
		return Optional.ofNullable(customers.get(customerId));
	}

	public static Optional<Product> getProductById(String productId) {
//		System.out.println(String.format(">> getProductById(%s) %tT %s", productId, new Date(), Thread.currentThread()));
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(String.format("<< getProductById %tT %s", new Date(), Thread.currentThread()));

		return Optional.ofNullable(products.get(productId));
	}
	
	private static int ratingid;

	public static Rating rateProduct(String productId, String customerId, int score, String comment) {
//		System.out.println(String.format(">> rateProduct(%s) %tT %s", productId, new Date(), Thread.currentThread()));
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(String.format("<< rateProduct %tT %s", new Date(), Thread.currentThread()));

		Product product = getProductById(productId).get();
		Customer customer = getCustomerById(customerId).get();
		Rating rating = new Rating("" + ratingid++, product, customer, score, comment); 
		
		if(cRatings.get(customer) == null) cRatings.put(customer, new ArrayList<>());
		if(pRatings.get(product) == null) pRatings.put(product, new ArrayList<>());
		cRatings.get(customer).add(rating);
		pRatings.get(product).add(rating);
		return rating;
	}

}
