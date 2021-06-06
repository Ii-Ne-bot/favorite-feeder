package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.config;

import java.io.Serializable;
import java.time.Duration;
import lombok.Data;

/**
 * API設定値
 */
@Data
public class ApiSetting implements Serializable {

    private static final long serialVersionUID = 2650544719026873628L;

    /**
     * URL
     */
    private String baseUrl;

    /**
     * 接続タイムアウト
     */
    private Duration connectTimeout;

    /**
     * 読み込みタイムアウト
     */
    private Duration readTimeout;

    /**
     * 最大メモリサイズ
     */
    private Integer maxInMemorySize;

    /**
     * API Key
     */
    private String apikey;

    /**
     * API Secret
     */
    private String apiSecret;
}
