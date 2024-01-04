package com.instream.auth.monolithic.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum DbStatus {
    USE,
    DISABLE,
    DELETED,
    FORCE_STOPPED;

    @JsonValue
    public String getCode() {
        return name();
    }

    @JsonCreator
    public static DbStatus fromCode(String code) {
        return Arrays.stream(DbStatus.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("DB 상태에 %s가 존재하지 않습니다.", code)));
    }
}
