package knjizara.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import knjizara.model.Izdavac;
import knjizara.model.Knjiga;
import knjizara.service.IzdavacService;
import knjizara.service.KnjigaService;
import knjizara.support.IzdavacToIzdavacDTO;
import knjizara.support.KnjigaToKnjigaDTO;
import knjizara.web.dto.IzdavacDTO;
import knjizara.web.dto.KnjigaDTO;

@RestController
@RequestMapping(value = "/api/izdavaci")
public class ApiIzdavacController {
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private IzdavacService izdavacService;
	
	@Autowired
	private IzdavacToIzdavacDTO toDTO;
	
	@Autowired
	private KnjigaToKnjigaDTO toKnjigaDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<IzdavacDTO>> getIzdavaci() {

		List<Izdavac> izdavaci = izdavacService.findAll();

		return new ResponseEntity<>(toDTO.convert(izdavaci), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<IzdavacDTO> getIzdavac(@PathVariable Long id) {
		Izdavac izdavac = izdavacService.findOne(id);
		if (izdavac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(izdavac), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{izdavacId}/knjige")
	public ResponseEntity<List<KnjigaDTO>> getKnjigaIzdavaca(
			@PathVariable Long izdavacId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Knjiga> knjige = knjigaService.findByIzdavacId(pageNum, izdavacId);
		
		return  new ResponseEntity<>(
				toKnjigaDTO.convert(knjige.getContent()),
				HttpStatus.OK);
	}

}
