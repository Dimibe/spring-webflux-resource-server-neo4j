package dev.begnis.webfluxexample;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingWebFilter implements WebFilter {

    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        return exchange.getPrincipal()
                       .doOnNext(p -> log.info("Request {} called by {}", path, ((CustomPrinciple) p).getUserId()))
                       .then(chain.filter(exchange));
    }
}
