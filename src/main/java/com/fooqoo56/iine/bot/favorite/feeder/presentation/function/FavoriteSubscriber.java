package com.fooqoo56.iine.bot.favorite.feeder.presentation.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooqoo56.iine.bot.favorite.feeder.presentation.function.dto.FeedTweet;
import com.fooqoo56.iine.bot.favorite.feeder.presentation.function.dto.PubSubMessage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FavoriteSubscriber {
    private final ObjectMapper objectMapper;

    /**
     * pubsubでメッセージを受け取る関数
     *
     * @return PubSubMessageを引数に持つ関数
     */
    @Bean
    @NonNull
    public Consumer<PubSubMessage> pubSubFunction() {
        return this::favoriteTweetFunction;
    }

    /**
     * ツイートをいいねする関数
     *
     * @param message Pub/Subからpublishされたメッセージ
     * @return ツイートのいいねに成功した場合、trueを返す
     */
    @NonNull
    private Boolean favoriteTweetFunction(final PubSubMessage message) {

        final FeedTweet feedTweet = mapTweetCondition(getDecodedMessage(message));

        log.info(feedTweet.toString());

        return Boolean.TRUE;
    }

    /**
     * pub/subのdataをツイート条件クラスにマッピングする
     *
     * @param data pub/subのdata
     * @return ツイート条件クラス
     */
    @NonNull
    private FeedTweet mapTweetCondition(final String data)
            throws RuntimeException {
        try {
            return objectMapper.readValue(data, FeedTweet.class);
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * メッセージをデコードする
     *
     * @param message pubsubメッセージ
     * @return デコードされたdataパラメータ
     */
    @NonNull
    private String getDecodedMessage(final PubSubMessage message) {
        return new String(Base64.getDecoder().decode(message.getData()),
                StandardCharsets.UTF_8);
    }
}
