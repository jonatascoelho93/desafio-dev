package br.com.bycoders.cadastrotransacaoservice.cotroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.cadastrotransacaoservice.VO.TransacaoAgrupadaVO;
import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;
import br.com.bycoders.cadastrotransacaoservice.repository.TransacaoRepository;
import br.com.bycoders.cadastrotransacaoservice.util.TransacaoUtil;

@RestController
@RequestMapping(path = "/transacao", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TransacaoController {

	public static final Logger logger = LoggerFactory.getLogger(TransacaoController.class);

	@Autowired
	TransacaoRepository transacaoRepository;

	@CrossOrigin
	@PostMapping(path = "/addAll")
	public ResponseEntity<?> upload(@RequestParam MultipartFile arquivo) {
		try {
			logger.info("Acessando endpoint post /trasacao/all");
			if (!arquivo.getOriginalFilename().endsWith(".txt")) {
				logger.error("Arquivo invalido");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<Transacao> transacoes = TransacaoUtil.convertFile(arquivo);
			return new ResponseEntity<>(transacaoRepository.saveAll(transacoes), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro ao fazer UPLOAD do arquivo: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<?> getAll() {
		try {
			logger.info("Acessando endpoint get /trasacao");
			List<TransacaoAgrupadaVO> transacoesAgrupada = TransacaoUtil
					.agruaparTransacoes(transacaoRepository.findByOrderByNomeLojaAsc());
			return new ResponseEntity<>(transacoesAgrupada, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Erro ao listar transações: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
