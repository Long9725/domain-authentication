package com.instream.auth.monolithic.domain.user.service;

import com.instream.auth.monolithic.domain.user.dto.SignUpUserCommand;
import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.domain.user.repository.UserRepository;
import com.instream.auth.monolithic.util.enums.DbStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserWriteService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto create(SignUpUserCommand command) {
        User user = User.builder()
                .account(command.account())
                .password(passwordEncoder.encode(command.password()))
                .nickname(command.nickname())
                .dbStatus(DbStatus.USE)
                .build();
        userRepository.save(user);
        return new UserDto(user.getId(), user.getAccount(), user.getNickname(), user.getApiKey());
    }
}
