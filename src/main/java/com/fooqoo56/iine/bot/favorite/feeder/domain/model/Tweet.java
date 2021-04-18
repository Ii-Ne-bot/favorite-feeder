package com.fooqoo56.iine.bot.favorite.feeder.domain.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * ツイート
 */
@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Tweet implements Serializable {

    private static final long serialVersionUID = 7724193033321337578L;

    /**
     * ツイートID
     */
    @NonNull
    private final String id;

    /**
     * ツイート本文
     */
    @NonNull
    private final String text;

    /**
     * リツイート数
     */
    @NonNull
    private final Integer retweetCount;

    /**
     * いいね数
     */
    @NonNull
    private final Integer favoriteCount;

    /**
     * ユーザ
     */
    @NonNull
    private final User user;

    /**
     * いいね済の場合、true
     */
    private final boolean favorite;

    /**
     * リツイート済の場合、true
     */
    private final boolean retweet;

    /**
     * センシティブ指定の場合、true
     */
    private final boolean sensitive;

    /**
     * 引用ツイートの場合、true
     */
    private final boolean quote;

    /**
     * リプライツイートの場合、true
     */
    private final boolean reply;

    /**
     * いいねが未実施かどうか
     *
     * @return いいねが未実施の場合、trueを返す
     */
    public boolean isNotFavorite() {
        return !favorite;
    }
}
