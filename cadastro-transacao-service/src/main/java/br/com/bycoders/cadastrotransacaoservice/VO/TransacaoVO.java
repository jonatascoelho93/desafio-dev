package br.com.bycoders.cadastrotransacaoservice.VO;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoVO {

	private Integer tipoCodigo;
	private String tipoDecricao;
	private String data;
	private BigDecimal valor;
	private String cpf;
	private String cartao;
	private String hora;
	private String donoLoja;
	private String nomeLoja;

	public TransacaoVO(Transacao transacao) {
		this.tipoCodigo = transacao.getTipo().getCodigo();
		this.tipoDecricao = transacao.getTipo().getDescricao();
		this.data = transacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.hora = transacao.getHora().toString();
		this.cpf = transacao.getCpf();
		this.cartao = transacao.getCartao();
		this.donoLoja = transacao.getDonoLoja();
		this.nomeLoja = transacao.getNomeLoja();
		this.valor = transacao.getValor();

	}

}
