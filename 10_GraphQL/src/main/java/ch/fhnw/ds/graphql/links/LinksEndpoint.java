package ch.fhnw.ds.graphql.links;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.ds.graphql.shop.ShopEndpoint;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.servlet.SimpleGraphQLHttpServlet;

// @WebServlet("/graphql")
public class LinksEndpoint extends HttpServlet {
	private static final long serialVersionUID = -2039054039717393249L;

	private SimpleGraphQLHttpServlet servlet = SimpleGraphQLHttpServlet.newBuilder(buildSchema())
			.build();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		servlet.service(req, resp);
	}
	
    private static GraphQLSchema buildSchema() {
        LinkRepository linkRepository = new LinkRepository();
        
        Reader streamReader = new InputStreamReader(ShopEndpoint.class.getResourceAsStream("/links.graphqls"));
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(streamReader);

        RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type(newTypeWiring("Query")
					.dataFetcher("allLinks", env -> {
						return linkRepository.getAllLinks();
					})
				)
				.type(newTypeWiring("Mutation")
					.dataFetcher("createLink", env -> {
						Link link = new Link(env.getArgument("url"), env.getArgument("description"));
						linkRepository.saveLink(link);
						return link;
					})
				)
                .build();
        
        return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

}
