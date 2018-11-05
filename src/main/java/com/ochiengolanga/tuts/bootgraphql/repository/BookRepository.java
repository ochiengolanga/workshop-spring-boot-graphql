package com.ochiengolanga.tuts.bootgraphql.repository;

import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
