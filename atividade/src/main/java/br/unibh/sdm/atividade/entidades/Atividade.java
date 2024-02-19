package br.unibh.sdm.atividade.entidades;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

/**
 * Classe contendo a definicao de Atividade
 *
 * @version 1.0
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Atividade other = (Atividade) obj;
		return Objects.equals(id, other.id) &&
				Objects.equals(nomeAtividade, other.nomeAtividade) &&
				Objects.equals(diaSemana, other.diaSemana) &&
				Objects.equals(horaInicio, other.horaInicio) &&
				Objects.equals(horaTermino, other.horaTermino) &&
				Objects.equals(repete, other.repete);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeAtividade, diaSemana, horaInicio, horaTermino, repete);
	}

}
