package com.ochiengolanga.tuts.bootgraphql.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @Column(name="book_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="author_first_name", nullable = false)
    private String firstName;

    @Column(name="author_last_name", nullable = false)
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
