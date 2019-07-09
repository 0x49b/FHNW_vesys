package ch.fhnw.ds.graphql.links;

import java.util.UUID;

public class Link {
    
    private final String id; //the new field
    private final String url;
    private final String description;

    public Link(String url, String description) {
        this(UUID.randomUUID().toString(), url, description);
    }

    private Link(String id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
    
}