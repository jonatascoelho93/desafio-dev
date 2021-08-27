package br.com.bycoders.cadastrotransacaoservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.cadastrotransacaoservice.VO.TransacaoAgrupadaVO;
import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;
import br.com.bycoders.cadastrotransacaoservice.enumeration.TipoTransacaoEnum;
import br.com.bycoders.cadastrotransacaoservice.util.TransacaoUtil;

@SpringBootTest
class CadastroTransacaoServiceApplicationTests {

	@Test
	public void deveFazerAConversaoDeUmArquivoTxtCorretamente() throws IOException {

		List<Transacao> lista = TransacaoUtil.convertFile(criarArquivoTxt());

		assertEquals(5, lista.size());
		assertEquals(TipoTransacaoEnum.DEBITO, lista.get(0).getTipo());
		assertEquals(LocalDate.parse("2019-03-01"), lista.get(0).getData());
		assertEquals(BigDecimal.valueOf(103).setScale(2), lista.get(0).getValor());
		assertEquals("23270298056", lista.get(0).getCpf());
		assertEquals("6777****1313", lista.get(0).getCartao());
		assertEquals(LocalTime.of(17, 27, 12), lista.get(0).getHora());
		assertEquals("JOSÉ COSTA", lista.get(0).getDonoLoja());
		assertEquals("MERCEARIA 3 IRMÃOS", lista.get(0).getNomeLoja());
	}

	@Test
	public void deveAgruparTrasacoesEFazerSomaCorreta() throws IOException {

		List<Transacao> lista = TransacaoUtil.convertFile(criarArquivoTxt());

		assertEquals(5, lista.size());

		List<TransacaoAgrupadaVO> trasacoesAgrupadas = TransacaoUtil.agruaparTransacoes(lista);

		assertEquals(2, trasacoesAgrupadas.size());
		assertEquals(BigDecimal.valueOf(-1).setScale(2), trasacoesAgrupadas.get(0).getSaldo());
		assertEquals(BigDecimal.valueOf(-609).setScale(2), trasacoesAgrupadas.get(1).getSaldo());

	}

	private MultipartFile criarArquivoTxt() {
		StringBuilder textoArquivo = new StringBuilder(
				"1201903010000010300232702980566777****1313172712JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n");
		textoArquivo.append("2201903010000010900232702980568723****9987123333JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n");
		textoArquivo.append("2201903010000010700845152540738723****9987123333MARCOS PEREIRAMERCADO DA AVENIDA\n");
		textoArquivo.append("1201903010000000500232702980567677****8778141808JOSÉ COSTA    MERCEARIA 3 IRMÃOS\n");
		textoArquivo.append("3201903010000050200845152540738473****1231231233MARCOS PEREIRAMERCADO DA AVENIDA\n");
		MultipartFile arquivo = new MockMultipartFile("texto", "texto.txt", MediaType.TEXT_PLAIN_VALUE,
				textoArquivo.toString().getBytes());

		return arquivo;

	}

}
