package restoran.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.service.JeloService;
import restoran.support.JeloDTOToJelo;
import restoran.support.JeloToJeloDTO;
import restoran.web.dto.JeloDTO;

@RestController
@RequestMapping(value="/api/jela")
public class ApiJeloController {
	
	@Autowired
	private JeloService jeloService;
	
	@Autowired
	private JeloToJeloDTO toDto;
	
	@Autowired
	private JeloDTOToJelo toJelo;

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<JeloDTO>> getJela(
			@RequestParam(required=false) String naziv,
			@RequestParam(value = "nazivV", required = false) String nazivV,
			@RequestParam(defaultValue="0") int page){
		Page<Jelo> jela;
		
		if(naziv != null ) {
			jela = jeloService.pretraga(naziv,  page);
		}else if (nazivV != null ) {
			jela = jeloService.findAllByVrstaJelaNazivLike(nazivV, page);
		}else{
			jela = jeloService.findAll(page);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(jela.getTotalPages()));
		return new ResponseEntity<>(toDto.convert(jela.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<JeloDTO> getJelo(@PathVariable Long id) {
		Jelo jelo = jeloService.findOne(id);
		if (jelo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(jelo), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<JeloDTO> add(@RequestBody JeloDTO newFood) {
		Jelo jelo = toJelo.convert(newFood);

		Jelo savedFood = jeloService.save(jelo);

		return new ResponseEntity<>(toDto.convert(savedFood), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<JeloDTO> edit(@RequestBody JeloDTO jelo,
			@PathVariable Long id) {

		if (id != jelo.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Jelo persisted = jeloService.save(toJelo.convert(jelo));

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<JeloDTO> delete(@PathVariable Long id) {
		Jelo deleted = jeloService.delete(id);

		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}	
	
}
