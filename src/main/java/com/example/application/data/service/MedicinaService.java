package com.example.application.data.service;

import com.example.application.data.entity.Medicina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MedicinaService {

    private final MedicinaRepository repository;

    @Autowired
    public MedicinaService(MedicinaRepository repository) {
        this.repository = repository;
    }

    public Optional<Medicina> get(UUID id) {
        return repository.findById(id);
    }

    public Medicina update(Medicina entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<Medicina> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
