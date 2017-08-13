package com.desabafo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Comentario {

	@Id
	@SequenceGenerator(name = "COMENT_SEQ", sequenceName = "COMENTSEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COMENT_SEQ")
	private Long id;

	private String texto;

	private int qntCurtidas;

	private Date dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}