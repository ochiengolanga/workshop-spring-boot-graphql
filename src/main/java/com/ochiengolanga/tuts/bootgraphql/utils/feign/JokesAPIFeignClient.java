package com.ochiengolanga.tuts.bootgraphql.utils.feign;

import com.ochiengolanga.tuts.bootgraphql.domain.dto.JokeWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="jokes-api", url = "https://api.icndb.com")
public interface JokesAPIFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/jokes/random")
    JokeWrapper getRandomJoke();

    @RequestMapping(method = RequestMethod.GET, path = "/jokes/{id}")
    JokeWrapper getJoke(@PathVariable("id") Long id);
}