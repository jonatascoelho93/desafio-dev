package br.com.bycoders.cadastrotransacaoservice.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.cadastrotransacaoservice.VO.TransacaoAgrupadaVO;
import br.com.bycoders.cadastrotransacaoservice.VO.TransacaoVO;
import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;
import br.com.bycoders.cadastrotransacaoservice.enumeration.TipoTransacaoEnum;

public class TransacaoUtil {

	public static List<Transacao> convertFile(MultipartFile arquivo) throws IOException {

		String conteudo = new String(arquivo.getBytes(), StandardCharsets.UTF_8);
		Stream<String> list = conteudo.lines();
		List<Transacao> trasacoes = new ArrayList<>();
		list.forEach(l -> trasacoes.add(createTransacao(l)));

		return trasacoes;
	}

	private static Transacao createTransacao(String l) {
		Transacao transacao = new Transacao();
		transacao.setTipo(TipoTransacaoEnum.valueOfCodigo(Integer.valueOf(l.substring(0, 1))));
		transacao.setData(LocalDate.of(Integer.valueOf(l.substring(1, 5)), Integer.valueOf(l.substring(5, 7)),
				Integer.valueOf(l.substring(7, 9))));
		transacao.setValor(BigDecimal
				.valueOf(Double.valueOf(transacao.getTipo().getOperacao() + l.substring(9, 19)) / 100).setScale(2));
		transacao.setCpf(l.substring(19, 30));
		transacao.setCartao(l.substring(30, 42));
		transacao.setHora(LocalTime.of(Integer.valueOf(l.substring(42, 44)), Integer.valueOf(l.substring(44, 46)),
				Integer.valueOf(l.substring(46, 48))));
		transacao.setDonoLoja(l.substring(48, 62).trim());
		transacao.setNomeLoja(l.substring(62, l.length()).trim());

		return transacao;
	}

	public static List<TransacaoAgrupadaVO> agruaparTransacoes(List<Transacao> transacoes) {

		List<TransacaoAgrupadaVO> transacaosAgrupadaVO = new ArrayList<TransacaoAgrupadaVO>();
		List<String> nomesLoja = transacoes.stream().map(Transacao::getNomeLoja).distinct()
				.collect(Collectors.toList());

		for (String nomeLoja : nomesLoja) {
			transacaosAgrupadaVO.add(new TransacaoAgrupadaVO(filtrarLojasPeloNome(nomeLoja, transacoes)));
		}

		return transacaosAgrupadaVO;

	}

	private static List<TransacaoVO> filtrarLojasPeloNome(String nomeLoja, List<Transacao> transacoes) {

		return transacoes.stream().filter(t -> nomeLoja.equals(t.getNomeLoja())).map(TransacaoVO::new)
				.collect(Collectors.toList());

	}

}
