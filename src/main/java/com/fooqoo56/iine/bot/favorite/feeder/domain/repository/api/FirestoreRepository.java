package com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api;

import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.UdbResponse;
import reactor.core.publisher.Mono;

/**
 * FireStoreに接続するAPI
 */
public interface FirestoreRepository {

    /**
     * ツイッターユーザを取得する
     *
     * @param userId  ユーザID
     * @param tweetId ツイートID
     * @return UdbResponse
     */
    Mono<UdbResponse> getOauth(final String userId, final String tweetId);
}
