package com.desabafo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Publicacao {

	@Id
	@SequenceGenerator(name = "PUB_SEQ", sequenceName = "PUBSEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PUB_SEQ")
	private Long id;

	@Column(nullable = false)
	private String texto;

	private int qntCurtidas;

	private int qntComentarios;

	private Date dataCriacao;

	private String assunto;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comentario> comentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getQntCurtidas() {
		return qntCurtidas;
	}

	public void setQntCurtidas(int qntCurtidas) {
		this.qntCurtidas = qntCurtidas;
	}

	public int getQntComentarios() {
		return qntComentarios;
	}

	public void setQntComentarios(int qntComentarios) {
		this.qntComentarios = qntComentarios;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}