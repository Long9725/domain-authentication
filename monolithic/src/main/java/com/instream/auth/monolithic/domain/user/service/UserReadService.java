package com.instream.auth.monolithic.domain.user.service;

import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserReadService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto getUser(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow();
        return new UserDto(user.getId(), user.getAccount(), user.getNickname(), user.getApiKey());
    }
}
