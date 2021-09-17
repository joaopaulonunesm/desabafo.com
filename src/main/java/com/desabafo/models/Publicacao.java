package com.desabafo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}