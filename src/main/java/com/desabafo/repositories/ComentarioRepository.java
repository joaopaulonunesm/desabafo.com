package com.desabafo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desabafo.models.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}