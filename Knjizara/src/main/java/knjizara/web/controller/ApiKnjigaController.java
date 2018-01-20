package knjizara.web.controller;

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

import knjizara.model.Knjiga;
import knjizara.model.Kupovina;
import knjizara.service.KnjigaService;
import knjizara.service.KupovinaService;
import knjizara.support.KnjigaDTOToKnjiga;
import knjizara.support.KnjigaToKnjigaDTO;
import knjizara.support.KupovinaToKupovinaDTO;
import knjizara.web.dto.KnjigaDTO;
import knjizara.web.dto.KupovinaDTO;

@RestController
@RequestMapping(value = "/api/knjige")
public class ApiKnjigaController {
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private KupovinaService kupovinaService;

	@Autowired
	private KnjigaDTOToKnjiga toKnjiga;

	@Autowired
	private KnjigaToKnjigaDTO toDTO;
	
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<KnjigaDTO>> getKnjige(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String pisac,
			@RequestParam(required=false) Integer gornjaKol,
			@RequestParam(defaultValue = "0") int page) {
		Page<Knjiga> knjige;
		
		if(naziv != null || pisac != null || gornjaKol != null) {
			knjige = knjigaService.pretraga(naziv, pisac, gornjaKol, page);
		}else{
			knjige = knjigaService.findAll(page);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(knjige.getTotalPages()));
		return new ResponseEntity<>(toDTO.convert(knjige.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<KnjigaDTO> getKnjiga(@PathVariable Long id) {
		Knjiga knjiga = knjigaService.findOne(id);
		if (knjiga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(knjiga), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<KnjigaDTO> delete(@PathVariable Long id) {
		Knjiga deleted = knjigaService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KnjigaDTO> add(@RequestBody KnjigaDTO newBoo) {
		Knjiga knjiga = toKnjiga.convert(newBoo);
		knjigaService.save(knjiga);

		return new ResponseEntity<>(toDTO.convert(knjiga), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<KnjigaDTO> edit(@RequestBody KnjigaDTO book,
			@PathVariable Long id) {

		if (id != book.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Knjiga persisted = knjigaService.save(toKnjiga.convert(book));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
	public ResponseEntity<KupovinaDTO> buy(@PathVariable Long id){
		
		Kupovina k = kupovinaService.buyABook(id);
		
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		
	}
	
}
