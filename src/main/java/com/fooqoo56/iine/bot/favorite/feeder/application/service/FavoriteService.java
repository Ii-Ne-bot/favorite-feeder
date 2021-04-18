package com.fooqoo56.iine.bot.favorite.feeder.application.service;

import com.fooqoo56.iine.bot.favorite.feeder.application.sharedservice.TwitterSharedService;
import com.fooqoo56.iine.bot.favorite.feeder.domain.model.Oauth;
import com.fooqoo56.iine.bot.favorite.feeder.domain.model.Tweet;
import com.fooqoo56.iine.bot.favorite.feeder.domain.repository.api.FirestoreRepository;
import com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto.UdbResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * いいねを実施するサービスクラス
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final TwitterSharedService twitterSharedService;
    private final FirestoreRepository firestoreRepository;

    /**
     * 要件の合致したツイートのいいね
     *
     * @return ツイートのいいねに成功した場合、trueを返す
     */
    @NonNull
    public Mono<Optional<Tweet>> favoriteTweet(final String tweetId, final String userId) {
        return getOauthHeader(userId, tweetId)
                .flatMap(oauthHeader -> twitterSharedService.favoriteTweet(tweetId, oauthHeader))
                // Mono.emptyの場合、Optional.emptyを返す
                .switchIfEmpty(Mono.just(Optional.empty()));
    }

    /**
     * ツイッターユーザ取得
     *
     * @param userId  userId
     * @param tweetId tweetId
     * @return ツイッターユーザ
     */
    @NonNull
    private Mono<String> getOauthHeader(final String userId, final String tweetId) {
        return firestoreRepository.getOauth(userId, tweetId)
                .map(this::buildOauth)
                .map(oauth -> oauth.getOauthAuthorizationHeader(tweetId));
    }

    /**
     * UserOauthを作成する
     *
     * @param udbResponse udbのAPIレスポンス
     * @return 認証ドメイン
     */
    @NonNull
    private Oauth buildOauth(final UdbResponse udbResponse) {
        final UdbResponse.OauthUserResponse oauthUserResponse = udbResponse.getOauthWithNullCheck();
        return Oauth.builder()
                .oauthTimestamp(oauthUserResponse.getOauthTimestampWithNullCheck())
                .oauthSignatureMethod(oauthUserResponse.getOauthSignatureMethodWithNullCheck())
                .oauthVersion(oauthUserResponse.getOauthVersionWithNullCheck())
                .oauthNonce(oauthUserResponse.getOauthNonceWithNullCheck())
                .oauthConsumerKey(oauthUserResponse.getOauthConsumerKeyWithNullCheck())
                .oauthToken(oauthUserResponse.getOauthTokenWithNullCheck())
                .oauthSignature(oauthUserResponse.getOauthSignatureWithNullCheck())
                .build();
    }
}
