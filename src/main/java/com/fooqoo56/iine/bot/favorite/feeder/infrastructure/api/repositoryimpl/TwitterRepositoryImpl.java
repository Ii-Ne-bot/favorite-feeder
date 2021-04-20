package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.repositoryimpl;

import com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api.TwitterRepository;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.TweetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ツイッターAPIのRepository実装クラス
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class TwitterRepositoryImpl implements TwitterRepository {

    private static final String ID_PARAM = "id";

    private final WebClient twitterFavoriteClient;


    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public Mono<TweetResponse> favoriteTweet(final String id, final String oauthHeader) {
        return twitterFavoriteClient
                .post()
                .uri(uriBuilder -> uriBuilder.queryParam(ID_PARAM, id).build())
                .header(HttpHeaders.AUTHORIZATION, oauthHeader)
                .retrieve()
                .bodyToMono(TweetResponse.class);
    }
}
