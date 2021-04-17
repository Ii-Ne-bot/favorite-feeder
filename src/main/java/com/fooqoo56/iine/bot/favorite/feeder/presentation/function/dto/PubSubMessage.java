package com.fooqoo56.iine.bot.favorite.feeder.presentation.function.dto;

import java.io.Serializable;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * Google Cloud PubsubからPublishされるメッセージ
 */
@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class PubSubMessage implements Serializable {

    private static final long serialVersionUID = 4289253545399316658L;

    /**
     * pub/subのペイロード
     */
    private final String data;

    /**
     * attributes
     */
    private final Map<String, String> attributes;

    /**
     * メッセージID
     */
    private final String messageId;

    /**
     * publishされた時刻
     */
    private final String publishTime;
}
