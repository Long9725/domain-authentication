package com.instream.auth.monolithic.domain.user.dto;

import java.util.UUID;

public record UserDtoWithoutApiKey(
        UUID userId,

        String account,

        String nickname
) {
}
