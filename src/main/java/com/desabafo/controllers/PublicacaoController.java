package com.desabafo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desabafo.models.Comentario;
import com.desabafo.models.Publicacao;
import com.desabafo.services.ComentarioService;
import com.desabafo.services.PublicacaoService;

@Controller
@RequiredArgsConstructor
public class PublicacaoController {

	private static final int QUANTIDADE_DE_CURTIDAS_MINIMA_PARA_O_TOP = 5;

	private final PublicacaoService publicacaoService;
	private final ComentarioService comentarioController;

	// Deletar Publicação
	@RequestMapping(value = "/publicacoes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Publicacao> deletePublicacao(@PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		if (publicacao == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<Comentario> comentarios = publicacao.getComentarios();

		comentarios.removeAll(comentarios);

		for (Comentario comentario : comentarios) {
			comentarioController.delete(comentario);
		}

		publicacaoService.delete(publicacao);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Get Publicação por id
	@RequestMapping(value = "/publicacoes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Publicacao> findPublicacaoById(@PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		publicacaoService.ordenarComentariosPorQntCurtidas(publicacao.getComentarios());

		return new ResponseEntity<>(publicacao, HttpStatus.OK);
	}

	// Get Publicações
	@RequestMapping(value = "/publicacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoes() {

		List<Publicacao> publicacoes = publicacaoService.findByOrderByDataCriacaoDesc();

		for (Publicacao publicacao : publicacoes) {
			publicacaoService.ordenarComentariosPorQntCurtidas(publicacao.getComentarios());
		}

		return new ResponseEntity<>(publicacoes, HttpStatus.OK);
	}

	// Get Publicações por Curtidas
	@RequestMapping(value = "/publicacoes/curtidas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoesPorCurtidas() {

		List<Publicacao> publicacoes = publicacaoService.findByOrderByQntCurtidas();

		for (Publicacao publicacao : publicacoes) {
			publicacaoService.ordenarComentariosPorQntCurtidas(publicacao.getComentarios());
		}

		List<Publicacao> topPublicacoes = new ArrayList<>();

		for (Publicacao publicacao : publicacoes) {
			if (publicacao.getQntCurtidas() >= QUANTIDADE_DE_CURTIDAS_MINIMA_PARA_O_TOP) {
				topPublicacoes.add(publicacao);
			}
		}

		return new ResponseEntity<>(topPublicacoes, HttpStatus.OK);
	}

	// Get Publicações por Assunto
	@RequestMapping(value = "/publicacoes/assunto/{assunto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoesPorAssunto(@PathVariable String assunto) {
		
		List<Publicacao> publicacoes = publicacaoService.findByAssuntoOrderByQntCurtidas(assunto);

		for (Publicacao publicacao : publicacoes) {
			publicacaoService.ordenarComentariosPorQntCurtidas(publicacao.getComentarios());
		}

		return new ResponseEntity<>(publicacoes, HttpStatus.OK);
	}

	// Get Assuntos que tem mais publicacao
	@RequestMapping(value = "/assuntos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAssuntosComMaisPublicacoes() {

		return new ResponseEntity<>(publicacaoService.findByAssuntoMaisFalado(), HttpStatus.OK);
	}

	// Get Assuntos ordenado por criacao
	@RequestMapping(value = "/assuntos/recentes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Publicacao>> findAssuntosMaisRecentes() {
		
		return new ResponseEntity<>(publicacaoService.findByAssuntoMaisRecente(), HttpStatus.OK);
	}

	// Criar nova Publicação
	@RequestMapping(value = "/publicacoes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Publicacao> postPublicacao(@RequestBody Publicacao publicacao) {

		if (publicacao.getTexto().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		publicacao.setQntComentarios(0);
		publicacao.setQntCurtidas(0);
		publicacao.setDataCriacao(new Date());

		return new ResponseEntity<>(publicacaoService.save(publicacao), HttpStatus.CREATED);
	}

	// Curtir uma Publicação
	@RequestMapping(value = "/publicacoes/curtir/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Publicacao> postCurtirPublicacao(@PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		if (publicacao == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		publicacao.setQntCurtidas(publicacao.getQntCurtidas() + 1);

		return new ResponseEntity<>(publicacaoService.save(publicacao), HttpStatus.OK);
	}

}