package ch.fhnw.ds.graphql.shop;

public class Rating {
	private String id;
	private Product product;
	private Customer customer;
	private int score;
	private String comment;

	public Rating(String id, Product product, Customer customer, int score, String comment) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
		this.score = score;
		this.comment = comment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
