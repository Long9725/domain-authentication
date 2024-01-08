package com.instream.auth.monolithic.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ApplicationType {
    CHAT,
    LIVE;

    @JsonValue
    public String getCode() {
        return name();
    }

    @JsonCreator
    public static ApplicationType fromCode(String code) {
        return Arrays.stream(ApplicationType.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("서비스 종류에 %s가 존재하지 않습니다.", code)));
    }
}
