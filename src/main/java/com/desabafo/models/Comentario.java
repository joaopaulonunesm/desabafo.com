package com.desabafo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

	@Id
	@SequenceGenerator(name = "COMENT_SEQ", sequenceName = "COMENTSEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COMENT_SEQ")
	private Long id;
	private String texto;
	private int qntCurtidas;
	private Date dataCriacao;
}