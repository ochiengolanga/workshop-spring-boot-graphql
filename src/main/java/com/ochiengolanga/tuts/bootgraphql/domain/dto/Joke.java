package com.ochiengolanga.tuts.bootgraphql.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Joke {
    private String id;
    private String joke;
}

