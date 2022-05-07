package com.example.application.data.service;

import com.example.application.data.entity.Correos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CorreosService {

    private final CorreosRepository repository;

    @Autowired
    public CorreosService(CorreosRepository repository) {
        this.repository = repository;
    }

    public Optional<Correos> get(UUID id) {
        return repository.findById(id);
    }

    public Correos update(Correos entity) {
        return repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Page<Correos> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}