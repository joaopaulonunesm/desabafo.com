package com.desabafo.controllers;

import java.util.Date;

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
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@Autowired
	PublicacaoService publicacaoService;

	// Deletar Comentario
	@RequestMapping(value = "/publicacoes/comentario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Publicacao> deletePublicacao(@PathVariable Long id) {

		Comentario comentario = comentarioService.findOne(id);

		if (comentario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		comentarioService.delete(comentario);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Criar novo Comentario
	@RequestMapping(value = "/publicacoes/comentar/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comentario> postPublicacao(@RequestBody Comentario comentario, @PathVariable Long id) {

		Publicacao publicacao = publicacaoService.findOne(id);

		comentario.setQntCurtidas(0);
		comentario.setDataCriacao(new Date());
		comentarioService.save(comentario);

		publicacao.getComentarios().add(comentario);
		publicacaoService.save(publicacao);

		return new ResponseEntity<>(comentario, HttpStatus.CREATED);
	}

	// Curtir um Comentario
	@RequestMapping(value = "/publicacoes/comentario/curtir/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comentario> postCurtirPublicacao(@PathVariable Long id) {

		Comentario comentario = comentarioService.findOne(id);

		if (comentario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		comentario.setQntCurtidas(comentario.getQntCurtidas() + 1);

		return new ResponseEntity<>(comentarioService.save(comentario), HttpStatus.OK);
	}

}