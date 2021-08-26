package br.com.bycoders.cadastrotransacaoservice.cotroller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.cadastrotransacaoservice.entity.Transacao;
import br.com.bycoders.cadastrotransacaoservice.repository.TransacaoRepository;
import br.com.bycoders.cadastrotransacaoservice.util.TransacaoUtil;

@RestController
@RequestMapping(path = "/transacao", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TransacaoController {

	public static final Logger logger = LoggerFactory.getLogger(TransacaoController.class);

	@Autowired
	TransacaoRepository transacaoRepository;

	@PostMapping(path = "/all")
	public ResponseEntity<?> upload(@RequestParam MultipartFile arquivo) {

		try {
			if (!arquivo.getOriginalFilename().endsWith(".txt")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<Transacao> transacoes = TransacaoUtil.convertFile(arquivo);
			return new ResponseEntity<>(transacaoRepository.saveAll(transacoes), HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
