package com.instream.auth.monolithic.domain.user.service;

import com.instream.auth.monolithic.domain.user.entity.User;
import com.instream.auth.monolithic.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserEntityReadService {
    private final UserRepository userRepository;

    public User getUser(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow();
    }
}
