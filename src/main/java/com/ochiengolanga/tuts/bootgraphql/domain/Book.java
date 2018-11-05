package com.ochiengolanga.tuts.bootgraphql.domain;

import com.ochiengolanga.tuts.bootgraphql.domain.dto.Joke;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @Column(name="book_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="book_title", nullable = false)
    private String title;

    @Column(name="book_isbn", nullable = false)
    private String isbn;

    @Column(name="book_pageCount", nullable = false)
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "author_id",
            nullable = false, updatable = false)
    private Author author;

    @Transient
    private Joke joke;

    public Book(String title, String isbn, int pageCount, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
    }
}
