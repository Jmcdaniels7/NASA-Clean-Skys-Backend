package com.example.clear_sky_backend.controller;

import com.example.clear_sky_backend.controller.dto.SubscriberRequest;
import com.example.clear_sky_backend.entity.Subscriber;
import com.example.clear_sky_backend.service.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.net.URI;

@RestController
@RequestMapping("/api/subscribers")
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriberController {

    private final SubscriberService service;

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SubscriberRequest req) {
        Subscriber created = service.save(req.getPhone(), req.getArea());
        return ResponseEntity.created(URI.create("/api/subscribers/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
