package com.instream.auth.monolithic.domain.user.dto;

import java.util.UUID;

public record UserDto(
        UUID userId,

        String account,

        String nickname,

        String apiKey
) {
}
