package com.fooqoo56.iine.bot.favorite.feeder.presentation.function.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * ツイート条件クラス
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class FeedTweet implements Serializable {

    private static final long serialVersionUID = -3737835488814731139L;

    public static final String PARAM_USER_ID = "userId";

    public static final String PARAM_TWEET_ID = "tweetId";

    /**
     * 対象ユーザ
     */
    private final String userId;

    /**
     * キーワード
     */
    private final String tweetId;

    /**
     * Json生成
     *
     * @param userId  ユーザID
     * @param tweetId ツイートID
     */
    @JsonCreator
    public FeedTweet(
            @JsonProperty(PARAM_USER_ID) final String userId,
            @JsonProperty(PARAM_TWEET_ID) final String tweetId
    ) {
        this.userId = userId;
        this.tweetId = tweetId;
    }
}
