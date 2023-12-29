package com.instream.auth.domain.monolithic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/thread")
public class ThreadController {
    @GetMapping
    public ResponseEntity<String> getThreadInfo() {
        return ResponseEntity.ok(Thread.currentThread().isVirtual() + " " + Thread.currentThread());
    }
}
