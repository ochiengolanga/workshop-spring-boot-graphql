package com.ochiengolanga.tuts.bootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import com.ochiengolanga.tuts.bootgraphql.domain.dto.Joke;
import com.ochiengolanga.tuts.bootgraphql.domain.dto.JokeWrapper;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.BookRepository;
import com.ochiengolanga.tuts.bootgraphql.utils.feign.JokesAPIService;

import java.util.Optional;

public class Query implements GraphQLQueryResolver {

    private final JokesAPIService jokesAPIService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Query(JokesAPIService jokesAPIService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.jokesAPIService = jokesAPIService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Joke findRandomJoke() {
        Optional<JokeWrapper> randomJoke = jokesAPIService.getRandomJoke();

        if(randomJoke.isPresent())
            return randomJoke.get().getValue();
        else
            return new Joke();
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
}