package com.fooqoo56.iine.bot.favorite.feeder.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * Udbのレスポンス
 */
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UdbResponse implements Serializable {

    private static final long serialVersionUID = 3733927787161098460L;

    private OauthUserResponse oauth;

    @NonNull
    public OauthUserResponse getOauthWithNullCheck() {
        return Objects.requireNonNull(oauth, "フィールドがnullです: UdbResponse.oauth");
    }

    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Builder
    public static class OauthUserResponse implements Serializable {

        private static final long serialVersionUID = 4834470782480293668L;

        private String oauthTimestamp;

        private String oauthSignatureMethod;

        private String oauthVersion;

        private String oauthNonce;

        private String oauthConsumerKey;

        private String oauthToken;

        private String oauthSignature;

        /**
         * oauthTimestamp取得(NullCheck実施)
         *
         * @return oauthTimestamp
         */
        @NonNull
        public String getOauthTimestampWithNullCheck() {
            return Objects
                    .requireNonNull(oauthTimestamp,
                            "フィールドがnullです: OauthUserResponse.oauthTimestamp");
        }

        /**
         * oauthSignatureMethod取得(NullCheck実施)
         *
         * @return oauthSignatureMethod
         */
        @NonNull
        public String getOauthSignatureMethodWithNullCheck() {
            return Objects.requireNonNull(oauthSignatureMethod,
                    "フィールドがnullです: OauthUserResponse.oauthSignatureMethod");
        }

        /**
         * oauthVersion取得(NullCheck実施)
         *
         * @return oauthVersion
         */
        @NonNull
        public String getOauthVersionWithNullCheck() {
            return Objects
                    .requireNonNull(oauthVersion, "フィールドがnullです: OauthUserResponse.oauthVersion");
        }

        /**
         * oauthNonce取得(NullCheck実施)
         *
         * @return oauthNonce
         */
        @NonNull
        public String getOauthNonceWithNullCheck() {
            return Objects.requireNonNull(oauthNonce, "フィールドがnullです: OauthUserResponse.oauthNonce");
        }

        /**
         * oauthConsumerKey取得(NullCheck実施)
         *
         * @return oauthConsumerKey
         */
        @NonNull
        public String getOauthConsumerKeyWithNullCheck() {
            return Objects
                    .requireNonNull(oauthConsumerKey,
                            "フィールドがnullです: OauthUserResponse.oauthConsumerKey");
        }

        /**
         * oauthToken取得(NullCheck実施)
         *
         * @return oauthToken
         */
        @NonNull
        public String getOauthTokenWithNullCheck() {
            return Objects.requireNonNull(oauthToken, "フィールドがnullです: OauthUserResponse.oauthToken");
        }

        /**
         * oauthSignature取得(NullCheck実施)
         *
         * @return oauthSignature
         */
        @NonNull
        public String getOauthSignatureWithNullCheck() {
            return Objects
                    .requireNonNull(oauthSignature,
                            "フィールドがnullです: OauthUserResponse.oauthSignature");
        }
    }
}
