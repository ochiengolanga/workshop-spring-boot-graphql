package com.ochiengolanga.tuts.bootgraphql.utils.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ochiengolanga.tuts.bootgraphql.domain.dto.JokeWrapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JokesAPIService {

    private final JokesAPIFeignClient feignClient;

    public JokesAPIService(JokesAPIFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @HystrixCommand(fallbackMethod = "getJokeFallback")
    public Optional<JokeWrapper> getJoke(Long id) {
        return Optional.of(feignClient.getJoke(id));
    }

    public Optional<JokeWrapper> getJokeFallback(Long id) {
        return Optional.empty();
    }

    @HystrixCommand(fallbackMethod = "getRandomJokeFallback")
    public Optional<JokeWrapper> getRandomJoke() {
        return Optional.of(feignClient.getRandomJoke());
    }

    public Optional<JokeWrapper> getRandomJokeFallback() {
        return Optional.empty();
    }

}