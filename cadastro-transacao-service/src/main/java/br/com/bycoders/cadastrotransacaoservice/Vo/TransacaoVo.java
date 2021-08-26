package br.com.bycoders.cadastrotransacaoservice.Vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.bycoders.cadastrotransacaoservice.enumeration.TipoTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoVo {

	private TipoTransacaoEnum tipo;
	private LocalDate data;
	private BigDecimal valor;
	private String cpf;
	private String cartao;
	private LocalTime hora;
	private String donoLoja;
	private String nomeLoja;

}
