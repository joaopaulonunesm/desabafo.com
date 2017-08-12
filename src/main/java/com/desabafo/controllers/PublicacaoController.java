package com.desabafo.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desabafo.models.Publicacao;
import com.desabafo.services.PublicacaoService;

@Controller
public class PublicacaoController {

	@Autowired
	private PublicacaoService publicacaoService;

	// Deletar Publicação
	@RequestMapping(value = "/publicacoes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Publicacao> deletePublicacao(@PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		if (publicacao == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		publicacaoService.delete(publicacao);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Get Publicações
	@RequestMapping(value = "/publicacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoes() {

		return new ResponseEntity<>(publicacaoService.findAll(), HttpStatus.OK);
	}

	// Get Publicações por Curtidas
	@RequestMapping(value = "/publicacoes/curtidas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoesPorCurtidas() {

		return new ResponseEntity<>(publicacaoService.findAll(), HttpStatus.OK);
	}

	// Get Publicações por Assunto
	@RequestMapping(value = "/publicacoes/assunto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllPublicacoesPorAssunto() {

		return new ResponseEntity<>(publicacaoService.findAll(), HttpStatus.OK);
	}

	// Get Assuntos
	@RequestMapping(value = "/assuntos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Publicacao>> findAllAssuntos() {

		return new ResponseEntity<>(publicacaoService.findAll(), HttpStatus.OK);
	}

	// Criar nova Publicação
	@RequestMapping(value = "/publicacoes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Publicacao> postPublicacao(@RequestBody Publicacao publicacao) {

		publicacao.setQntComentarios(0);
		publicacao.setQntCurtidas(0);
		publicacao.setDataCriacao(new Date());

		return new ResponseEntity<>(publicacaoService.save(publicacao), HttpStatus.CREATED);
	}

	// Curtir uma Publicação
	@RequestMapping(value = "/publicacoes/curtir/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Publicacao> postCurtirPublicacao(@PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		if (publicacao == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		publicacao.setQntCurtidas(publicacao.getQntCurtidas() + 1);

		return new ResponseEntity<>(publicacaoService.save(publicacao), HttpStatus.OK);
	}

}