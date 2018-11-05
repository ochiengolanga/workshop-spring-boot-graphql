package com.ochiengolanga.tuts.bootgraphql;

import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import com.ochiengolanga.tuts.bootgraphql.exception.GraphQLErrorAdapter;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.BookRepository;
import com.ochiengolanga.tuts.bootgraphql.resolvers.BookResolver;
import com.ochiengolanga.tuts.bootgraphql.resolvers.Query;
import com.ochiengolanga.tuts.bootgraphql.utils.feign.JokesAPIService;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableFeignClients
@SpringBootApplication
public class BootGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGraphqlApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository, JokesAPIService jokesAPIService) {
        return new BookResolver(authorRepository, jokesAPIService);
    }

    @Bean
    public Query query(JokesAPIService jokesAPIService, AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(jokesAPIService, authorRepository, bookRepository);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
            Author author = new Author("Herbert", "Schildt");
            authorRepository.save(author);

            bookRepository.save(new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
            bookRepository.save(new Book("Scala: A Beginner's Guide, First Edition", "0071809253", 1728, author));
        };
    }
}
