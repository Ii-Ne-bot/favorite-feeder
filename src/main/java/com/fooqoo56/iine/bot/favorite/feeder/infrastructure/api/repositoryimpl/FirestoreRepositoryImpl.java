package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.repositoryimpl;

import com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api.FirestoreRepository;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.UdbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Profile("prod")
public class FirestoreRepositoryImpl implements FirestoreRepository {

    private static final String BEARER_PREFIX = "Bearer ";

    private final WebClient bearerTokenGoogleClient;
    private final WebClient udbClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<UdbResponse> getOauth(final String userId, final String tweetId) {
        return getBearerToken()
                .flatMap(token -> udbClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("{id}")
                                .queryParam("tweetId", tweetId)
                                .build(userId))
                        .header(HttpHeaders.AUTHORIZATION,
                                BEARER_PREFIX + token)
                        .retrieve()
                        .bodyToMono(UdbResponse.class));
    }

    /**
     * BearerToken取得
     *
     * @return bearerToken
     */
    protected Mono<String> getBearerToken() {
        return bearerTokenGoogleClient
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }
}
