package br.com.bycoders.cadastrotransacaoservice.enumeration;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@SuppressWarnings("unused")
@Getter
public enum TipoTransacaoEnum {

	DEBITO(1, "Débito", "Entrada", "+"), BOLETO(2, "Boleto", "Saída", "-"),
	FINANCIAMENTO(3, "Financiamento", "Saída", "-"), CREDITO(4, "Crédito", "Entrada", "+"),
	RECEBIMENTO(5, "Recebimento Empréstimo", "Entrada", "+"), VENDAS(6, "Vendas", "Entrada", "+"),
	RECEBIMENTO_TED(7, "Recebimento TED", "Entrada", "+"), RECEBIMENTO_DOC(8, "Recebimento DOC", "Entrada", "+"),
	ALUGUEL(9, "Aluguel", "Saída", "-");

	private final Integer codigo;
	private final String descricao;
	private final String natureza;
	private final String operacao;

	private static final Map<Integer, TipoTransacaoEnum> LOOKUP = new HashMap<>();

	static {
		for (TipoTransacaoEnum e : TipoTransacaoEnum.values()) {
			LOOKUP.put(e.codigo, e);
		}
	}

	public static TipoTransacaoEnum valueOfCodigo(Integer codigo) {
		return codigo != null ? LOOKUP.get(codigo) : null;
	}

	TipoTransacaoEnum(Integer codigo, String descricao, String natureza, String operacao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.natureza = natureza;
		this.operacao = operacao;
	}

}
