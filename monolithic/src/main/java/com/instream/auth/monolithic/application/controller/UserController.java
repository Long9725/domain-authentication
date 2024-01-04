package com.instream.auth.monolithic.application.controller;

import com.instream.auth.monolithic.domain.user.dto.SignInUserCommand;
import com.instream.auth.monolithic.domain.user.dto.SignUpUserCommand;
import com.instream.auth.monolithic.domain.user.dto.UserDto;
import com.instream.auth.monolithic.domain.user.service.UserReadService;
import com.instream.auth.monolithic.domain.user.service.UserWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserWriteService userWriteService;

    private final UserReadService userReadService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        UserDto userDto = userReadService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpUserCommand command) {
        UserDto userDto = userWriteService.create(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody @Valid SignInUserCommand command) {
        UserDto userDto = userReadService.signIn(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
