package com.pala.reactive.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.stream.Stream;

@RestController
@Slf4j
public class TemperatureController {

    @GetMapping(value = "/temperatures", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getTemperature() {
        SecureRandom r = new SecureRandom();
        int low = 10;
        int high = 50;
        return Flux.fromStream(Stream.generate(() -> r.nextInt(high - low) + low)
                .map(s -> String.valueOf(s))
                .peek((msg) -> {
                    log.info(msg);
                }))
                .map(s -> Integer.valueOf(s))
                .delayElements(Duration.ofSeconds(1));

    }
}
