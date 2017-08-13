package com.desabafo.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desabafo.models.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

	List<Publicacao> findByOrderByDataCriacaoDesc();

	List<Publicacao> findByOrderByQntCurtidasDesc();

	List<Publicacao> findByAssuntoOrderByQntCurtidasDesc(String assunto);
	
	@Query("select p.assunto , count(p.id) as i from Publicacao p where p.assunto is not null group by p.assunto order by i desc")
	List<Publicacao> findByAssuntoMaisFalado();

	@Query("select p.assunto from Publicacao p where p.assunto is not null  order by p.dataCriacao desc")
	Set<Publicacao> findByAssuntoMaisRecente();

}