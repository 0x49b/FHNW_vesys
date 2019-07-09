package ch.fhnw.ds.graphql.shop;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.servlet.SimpleGraphQLHttpServlet;

// @WebServlet("/graphql")
public class ShopEndpoint extends HttpServlet {
	private static final long serialVersionUID = -8518583680933827366L;

	private SimpleGraphQLHttpServlet graphQLServlet;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		graphQLServlet.service(req, resp);
	}

	@Override
	public void init() throws ServletException {
		graphQLServlet = SimpleGraphQLHttpServlet.newBuilder(buildSchema()).build();
	}

    private GraphQLSchema buildSchema() {
        Reader streamReader = new InputStreamReader(ShopEndpoint.class.getResourceAsStream("/shop.graphqls"));
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(streamReader);
        
		RuntimeWiring wiring = newRuntimeWiring()
				.type(newTypeWiring("Query")
					.dataFetcher("getProduct", env -> {
						return Repository.getProductById(env.getArgument("productId"));
					})
				)
				.type(newTypeWiring("Mutation")
					// rateProduct(productId: ID!, customerId: ID!, socre: Int!, comment: String!) : Rating!
					.dataFetcher("rateProduct", env -> {
						return Repository.rateProduct(env.getArgument("productId"), env.getArgument("customerId"), env.getArgument("score"), env.getArgument("comment"));
					})
				)
				.type(newTypeWiring("Product")
					.dataFetcher("ratings", env -> {
						return Repository.pRatings.getOrDefault(env.getSource(), new ArrayList<Rating>());
					})
					.dataFetcher("averageRatingScore", env -> {
						Product product = (Product)env.getSource();
						// Repository.pRatings.getOrDefault(product,  new ArrayList<>()).stream().mapToInt(r -> r.getScore()).forEach(x -> System.out.println(x));
						return Repository.pRatings.getOrDefault(product,  new ArrayList<>()).stream().mapToInt(r -> r.getScore()).average().getAsDouble();
					})
				)
				.type(newTypeWiring("Customer")
						.dataFetcher("ratings", env -> Repository.cRatings.get(env.getSource()))
				)
				.build();

        return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
    }

}
