package com.ochiengolanga.tuts.bootgraphql.repository;

import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
