package com.example.application.data.service;

import com.example.application.data.entity.Correos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CorreosRepository extends JpaRepository<Correos, UUID> {

}