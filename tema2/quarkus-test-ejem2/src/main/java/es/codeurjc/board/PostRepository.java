package es.codeurjc.board;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    public Collection<Post> findByUsername(String username) {
        return list("username", username);
    }
}