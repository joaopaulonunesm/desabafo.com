package com.desabafo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desabafo.models.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

}