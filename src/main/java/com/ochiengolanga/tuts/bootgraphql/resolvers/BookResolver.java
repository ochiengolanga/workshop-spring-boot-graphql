package com.ochiengolanga.tuts.bootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import com.ochiengolanga.tuts.bootgraphql.domain.dto.Joke;
import com.ochiengolanga.tuts.bootgraphql.domain.dto.JokeWrapper;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.utils.feign.JokesAPIService;

import java.util.Optional;

public class BookResolver implements GraphQLResolver<Book> {

    private final AuthorRepository authorRepository;
    private final JokesAPIService jokesAPIService;

    public BookResolver(AuthorRepository authorRepository, JokesAPIService jokesAPIService) {
        this.authorRepository = authorRepository;
        this.jokesAPIService = jokesAPIService;
    }

    public Optional<Joke> getJoke(Book book) {
        return jokesAPIService.getJoke(book.getId()).map(JokeWrapper::getValue);
    }

    public Optional<Author> getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId());
    }
}