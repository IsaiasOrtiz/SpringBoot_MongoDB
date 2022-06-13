package com.confetystudios.test.controllermongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.confetystudios.test.dto.UsuariosDocument;
import com.confetystudios.test.interfaces.UsuariosInterface;

@RestController
@RequestMapping("/MongoTest")
public class UsuariosController {

	Logger logger = LoggerFactory.getLogger(UsuariosController.class);

	@Autowired
	private UsuariosInterface usuariosInterface;

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostMapping
	public ResponseEntity<?> saveEntity() {
		logger.info("UsuariosController.saveEntity()");
		try {
			UsuariosDocument usuario = new UsuariosDocument();
			List<String> telefonos = new ArrayList<>();
			telefonos.add("7777-7777");
			telefonos.add("7777-7777");
			usuario.setId(2);
			usuario.setNombre("Douglas Valle");
			usuario.setDireccion("El Salvador");
			usuario.setUsuario("confety");
			usuario.setClave("************");
			usuario.setTelefonos(telefonos);
			UsuariosDocument usuariosave = usuariosInterface.save(usuario);
			return new ResponseEntity<UsuariosDocument>(usuariosave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> getEntitys() {
		try {

			List<UsuariosDocument> all = usuariosInterface.findAll();
			logger.info("UsuariosController.getEntitys()");
			return new ResponseEntity<List<?>>(all, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/custom/{name}")
	public ResponseEntity<?> getCustomName(@PathVariable("name") String name) {
		logger.info("UsuariosController.getCustomName(String name))");
		try {

			List<UsuariosDocument> all = usuariosInterface.findItemByName(name);
			if (all != null && !all.isEmpty()) {
				return new ResponseEntity<String>("No resoure found", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<?>>(all, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/template")
	public ResponseEntity<?> getEntitiesWithTemplate() {
		logger.info("UsuariosController.getCustomName(String name))");
		try {
			List<UsuariosDocument> all = mongoTemplate.findAll(UsuariosDocument.class);
			return new ResponseEntity<List<?>>(all, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/template/{criterio}")
	public ResponseEntity<?> getEntitiesWithTemplateCriterio(@PathVariable("criterio") String criterio , @RequestHeader("header") Optional<String> is) {
		logger.info("UsuariosController.getCustomName(String criterio)) header  " + is);
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("usuario").is(criterio));
			List<UsuariosDocument> all = mongoTemplate.find(query, UsuariosDocument.class);
			return new ResponseEntity<List<?>>(all, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

}
