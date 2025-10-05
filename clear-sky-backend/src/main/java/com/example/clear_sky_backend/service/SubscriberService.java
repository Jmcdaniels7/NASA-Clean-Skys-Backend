package com.example.clear_sky_backend.service;

import com.example.clear_sky_backend.entity.Subscriber;
import com.example.clear_sky_backend.repository.SubscriberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class SubscriberService {

    private final SubscriberRepository repository;

    public SubscriberService(SubscriberRepository repository) {
        this.repository = repository;
    }

    public Subscriber save(String phone) {
        return save(phone, null);
    }

    public Subscriber save(String phone, String area) {
        Optional<Subscriber> existing = repository.findByPhone(phone);
        if (existing.isPresent()) {
            // update area if provided
            Subscriber s = existing.get();
            if (area != null && !area.isBlank()) {
                s.setArea(area);
                return repository.save(s);
            }
            return s;
        }
        Subscriber sub = new Subscriber(phone, area);
        return repository.save(sub);
    }

    public Optional<Subscriber> findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    public List<Subscriber> findAll() {
        return repository.findAll();
    }
}
