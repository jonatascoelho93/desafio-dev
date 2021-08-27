package br.com.bycoders.cadastrotransacaoservice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.bycoders.cadastrotransacaoservice.enumeration.TipoTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class Transacao extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private TipoTransacaoEnum tipo;
	private LocalDate data;
	private BigDecimal valor;
	private String cpf;
	private String cartao;
	private LocalTime hora;
	private String donoLoja;
	private String nomeLoja;

}
