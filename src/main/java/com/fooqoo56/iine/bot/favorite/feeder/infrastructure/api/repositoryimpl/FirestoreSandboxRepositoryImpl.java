package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.repositoryimpl;

import com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api.FirestoreRepository;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.UdbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Profile("local")
public class FirestoreSandboxRepositoryImpl implements FirestoreRepository {

    private final WebClient udbClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<UdbResponse> getOauth(final String userId, final String tweetId) {
        return udbClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("{id}")
                        .queryParam("tweetId", tweetId)
                        .build(userId))
                .retrieve()
                .bodyToMono(UdbResponse.class);

    }
}
