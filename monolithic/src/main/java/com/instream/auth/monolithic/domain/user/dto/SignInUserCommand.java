package com.instream.auth.monolithic.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignInUserCommand(
        @Pattern(
                regexp = "^[A-Za-z\\d]{8,20}$",
                message = "계정 이름은 최소 8자리, 최대 20자리이며, 영어 소문자, 대문자, 숫자만 포함할 수 있습니다."
        )
        String account,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "비밀번호는 최소 8자리, 최대 20자리이며, 영어 소문자, 대문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다."
        )
        String password
) {
}
