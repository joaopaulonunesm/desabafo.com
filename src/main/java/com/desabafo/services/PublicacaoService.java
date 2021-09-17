package com.desabafo.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desabafo.models.Comentario;
import com.desabafo.models.Publicacao;
import com.desabafo.repositories.PublicacaoRepository;

@Service
public class PublicacaoService {

	@Autowired
	private PublicacaoRepository publicacaoRepository;

	public Publicacao save(Publicacao publicacao) {
		return publicacaoRepository.save(publicacao);
	}

	public void delete(Publicacao publicacao) {
		publicacaoRepository.delete(publicacao);
	}

	public Publicacao findOne(Long id) {
		return publicacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Publicação por ID não encontrada"));
	}

	public List<Publicacao> findByOrderByDataCriacaoDesc() {
		return publicacaoRepository.findByOrderByDataCriacaoDesc();
	}

	public List<Publicacao> findByOrderByQntCurtidas() {
		return publicacaoRepository.findByOrderByQntCurtidasDesc();
	}

	public List<Publicacao> findByAssuntoOrderByQntCurtidas(String assunto) {
		return publicacaoRepository.findByAssuntoOrderByQntCurtidasDesc(assunto);
	}
	
	public List<Publicacao> findByAssuntoMaisFalado() {
		return publicacaoRepository.findByAssuntoMaisFalado();
	}

	public Set<Publicacao> findByAssuntoMaisRecente() {
		return publicacaoRepository.findByAssuntoMaisRecente();
	}
	
	public void ordenarComentariosPorQntCurtidas(List<Comentario> comentarios) {
		Comparator<Comentario> comparator = (comentario1, comentario2) -> {
			if (comentario1.getQntCurtidas() > comentario2.getQntCurtidas()) {
				return -1;
			}
			if (comentario1.getQntCurtidas() < comentario2.getQntCurtidas()) {
				return 1;
			}
			return 0;
		};
		comentarios.sort(comparator);
	}

}