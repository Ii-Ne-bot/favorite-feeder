package com.fooqoo56.iine.bot.favorite.feeder.application.sharedservice;

import com.fooqoo56.iine.bot.favorite.feeder.domain.model.Tweet;
import com.fooqoo56.iine.bot.favorite.feeder.domain.model.User;
import com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api.TwitterRepository;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.TweetResponse;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.UserResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * TwitterAPIの共通サービス層
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterSharedService {

    private final TwitterRepository twitterRepository;

    /**
     * ツイートをいいねする
     *
     * @param tweetId     ツイートID
     * @param oauthHeader 認証ヘッダ
     * @return TweetのOptional
     */
    @NonNull
    public Mono<Optional<Tweet>> favoriteTweet(final String tweetId, final String oauthHeader) {
        return twitterRepository.favoriteTweet(tweetId, oauthHeader)
                .map(this::buildTweet)
                .map(Optional::of)
                .onErrorResume(WebClientResponseException.class,
                        exception -> {
                            log.error(exception.toString());
                            return Mono.just(Optional.empty());
                        });
    }

    /**
     * ツイートドメインを作成する
     *
     * @param tweetResponse 検索APIのレスポンス
     * @return ツイートドメイン
     */
    @NonNull
    private Tweet buildTweet(final TweetResponse tweetResponse) {
        return Tweet.builder()
                .id(tweetResponse.getIdWithNullCheck())
                .text(tweetResponse.getTextWithNullCheck())
                .retweetCount(tweetResponse.getRetweetCountWithNullCheck())
                .favoriteCount(tweetResponse.getFavoriteCountWithNullCheck())
                .favorite(tweetResponse.isFavorite())
                .retweet(tweetResponse.isRetweet())
                .sensitive(tweetResponse.isSensitive())
                .quote(tweetResponse.isQuote())
                .reply(tweetResponse.isReply())
                .user(buildUser(tweetResponse.getUserWithNullCheck()))
                .build();
    }

    /**
     * ユーザドメインを作成する
     *
     * @param userResponse ユーザレスポンス
     * @return ユーザドメイン
     */
    @NonNull
    private User buildUser(final UserResponse userResponse) {
        return User.builder()
                .id(userResponse.getIdWithNullCheck())
                .followersCount(userResponse.getFollowersCountWithNullCheck())
                .friendsCount(userResponse.getFriendsCountWithNullCheck())
                .listedCount(userResponse.getListedCountWithNullCheck())
                .favouritesCount(userResponse.getFavouritesCountWithNullCheck())
                .statusesCount(userResponse.getStatusesCountWithNullCheck())
                .follow(userResponse.isFollow())
                .defaultProfile(userResponse.isDefaultProfile())
                .defaultProfileImage(userResponse.isDefaultProfileImage())
                .build();
    }
}
