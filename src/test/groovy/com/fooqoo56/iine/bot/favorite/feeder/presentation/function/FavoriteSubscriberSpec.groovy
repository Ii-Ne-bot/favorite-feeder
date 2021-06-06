package com.fooqoo56.iine.bot.favorite.feeder.presentation.function

import com.fasterxml.jackson.databind.ObjectMapper
import com.fooqoo56.iine.bot.favorite.feeder.application.service.FavoriteService
import com.fooqoo56.iine.bot.favorite.feeder.domain.model.Tweet
import com.fooqoo56.iine.bot.favorite.feeder.presentation.function.dto.PubSubMessage
import reactor.core.publisher.Mono
import spock.lang.Specification

/**
 * FavoriteSubscriberのテスト
 */
class FavoriteSubscriberSpec extends Specification {

    private FavoriteSubscriber sut
    private FavoriteService favoriteService;

    final setup() {
        favoriteService = Mock(FavoriteService) {
            favoriteTweet(*_) >> Mono.just(Optional.of(Mock(Tweet)))
        }
        sut = new FavoriteSubscriber(new ObjectMapper(), favoriteService)
    }

    final "favoriteTweetFunction"() {
        given:
        // 引数を生成する
        final message = PubSubMessage.builder()
                .data("eyJ1c2VySWQiOiAidXNlcklkIiwgInR3ZWV0SWQiOiAidHdlZXRJZCJ9Cg==")
                .attributes(Map.of("key", "value"))
                .messageId("id")
                .publishTime("publishTime")
                .build()

        when:
        final actual = sut.favoriteTweetFunction(message)

        then:
        actual == Boolean.TRUE
    }
}
