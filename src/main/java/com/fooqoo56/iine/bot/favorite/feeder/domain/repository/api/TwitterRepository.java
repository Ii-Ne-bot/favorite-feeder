package com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api;

import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.TweetResponse;
import reactor.core.publisher.Mono;

/**
 * ツイッターAPI
 */
public interface TwitterRepository {

    /**
     * ツイートのいいね
     *
     * @param id          ツイートID
     * @param oauthHeader Oauth1.0aヘッダ
     * @return いいねAPIのレスポンス
     */
    Mono<TweetResponse> favoriteTweet(final String id, final String oauthHeader);
}
