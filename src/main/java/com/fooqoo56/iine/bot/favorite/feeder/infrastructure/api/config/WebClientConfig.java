package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.config;

import io.netty.channel.ChannelOption;
import io.netty.resolver.DefaultAddressResolverGroup;
import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * WebClientの設定クラス
 */
@Configuration
public class WebClientConfig {

    /**
     * connector
     */
    private static final BiFunction<Duration, Duration, ReactorClientHttpConnector> CONNECTOR =
            (connectTimeout, readTimeout) -> new ReactorClientHttpConnector(HttpClient.create()
                    .responseTimeout(readTimeout)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout.toMillisPart())
                    .resolver(DefaultAddressResolverGroup.INSTANCE));

    /**
     * strategy
     */
    private static final Function<Integer, ExchangeStrategies> STRATEGY =
            (maxInMemorySize) -> ExchangeStrategies.builder()
                    .codecs(configurer -> configurer
                            .defaultCodecs()
                            .maxInMemorySize(maxInMemorySize))
                    .build();


    /**
     * ツイートいいねAPIの設定
     *
     * @return TwitterSetting
     */
    @Bean
    @ConfigurationProperties(prefix = "extension.api.twitter.favorite")
    public ApiSetting twitterFavoriteApiSetting() {
        return new ApiSetting();
    }

    /**
     * Googleの認証APIの設定
     *
     * @return GoogleSetting
     */
    @Bean
    @ConfigurationProperties(prefix = "extension.api.google.auth")
    public ApiSetting googleAuthApiSetting() {
        return new ApiSetting();
    }

    /**
     * UDBの取得API
     *
     * @return UDB
     */
    @Bean
    @ConfigurationProperties(prefix = "extension.api.google.udb")
    public ApiSetting udbApiSetting() {
        return new ApiSetting();
    }

    /**
     * いいねAPIクライアント
     *
     * @param apiSetting API設定
     * @return WebClient
     */
    @Bean
    @NonNull
    public WebClient twitterFavoriteClient(
            @Qualifier(value = "twitterFavoriteApiSetting") final ApiSetting apiSetting) {

        final ReactorClientHttpConnector connector = CONNECTOR
                .apply(apiSetting.getConnectTimeout(), apiSetting.getReadTimeout());

        return WebClient.builder()
                .baseUrl(apiSetting.getBaseUrl())
                .exchangeStrategies(STRATEGY.apply(apiSetting.getMaxInMemorySize()))
                .clientConnector(connector)
                .build();
    }

    /**
     * Googleの認証トークンを取得するWebClient取得
     *
     * @param apiSetting API設定
     * @return WebClient
     */
    @Bean
    @NonNull
    public WebClient bearerTokenGoogleClient(
            @Qualifier(value = "googleAuthApiSetting") final ApiSetting apiSetting) {

        final ReactorClientHttpConnector connector = CONNECTOR
                .apply(apiSetting.getConnectTimeout(), apiSetting.getReadTimeout());

        return WebClient.builder()
                .baseUrl(apiSetting.getBaseUrl())
                .exchangeStrategies(STRATEGY.apply(apiSetting.getMaxInMemorySize()))
                .clientConnector(connector)
                .defaultHeader("Metadata-Flavor", "Google")
                .build();

    }

    /**
     * ユーザ情報を取得するWebClient取得
     *
     * @param apiSetting API設定
     * @return WebClient
     */
    @Bean
    @NonNull
    public WebClient udbClient(
            @Qualifier(value = "udbApiSetting") final ApiSetting apiSetting) {

        final ReactorClientHttpConnector connector = CONNECTOR
                .apply(apiSetting.getConnectTimeout(), apiSetting.getReadTimeout());

        return WebClient.builder()
                .baseUrl(apiSetting.getBaseUrl())
                .exchangeStrategies(STRATEGY.apply(apiSetting.getMaxInMemorySize()))
                .clientConnector(connector)
                .build();

    }
}
