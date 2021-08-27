package br.com.bycoders.cadastrotransacaoservice.VO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoAgrupadaVO {

	private String nomeLoja;
	private BigDecimal saldo;
	private List<TransacaoVO> transacoes;

	public TransacaoAgrupadaVO(List<TransacaoVO> trasacoes) {
		this.nomeLoja = trasacoes.get(0).getNomeLoja();
		this.transacoes = trasacoes;
		this.saldo = trasacoes.stream().map(t -> t.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);

	}

}
