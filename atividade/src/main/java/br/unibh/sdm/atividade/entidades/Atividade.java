package br.unibh.sdm.atividade.entidades;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atividade")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(length = 255)
	private String nomeAtividade;

	@NotBlank
	@Column(length = 255)
	private String diaSemana;

	@NotBlank
	@Column(length = 255)
	private String horaInicio;

	@NotBlank
	@Column(length = 255)
	private String horaTermino;

	@NotBlank
	@Column(length = 3)
	private String repete;

	public Atividade() {
		super();
	}

	public Atividade(Long id, String nomeAtividade, String diaSemana, String horaInicio, String horaTermino, String repete) {
		super();
		this.id = id;
		this.nomeAtividade = nomeAtividade;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.repete = repete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getRepete() {
		return repete;
	}

	public void setRepete(String repete) {
		this.repete = repete;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", nomeAtividade=" + nomeAtividade + ", diaSemana=" + diaSemana + ", horaInicio=" + horaInicio
				+ ", horaTermino=" + horaTermino + ", repete=" + repete + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaSemana == null) ? 0 : diaSemana.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((horaTermino == null) ? 0 : horaTermino.hashCode());
		result = prime * result + ((nomeAtividade == null) ? 0 : nomeAtividade.hashCode());
		result = prime * result + ((repete == null) ? 0 : repete.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (diaSemana == null) {
			if (other.diaSemana != null)
				return false;
		} else if (!diaSemana.equals(other.diaSemana))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (horaTermino == null) {
			if (other.horaTermino != null)
				return false;
		} else if (!horaTermino.equals(other.horaTermino))
			return false;
		if (nomeAtividade == null) {
			if (other.nomeAtividade != null)
				return false;
		} else if (!nomeAtividade.equals(other.nomeAtividade))
			return false;
		if (repete == null) {
			if (other.repete != null)
				return false;
		} else if (!repete.equals(other.repete))
			return false;
		return true;
	}

}
