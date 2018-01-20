package restoran.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.VrstaJela;
import restoran.service.VrstaJelaService;
import restoran.support.VrstaJelaToVrstaJelaDTO;
import restoran.web.dto.VrstaJelaDTO;

@RestController
@RequestMapping(value="/api/vrsteJela")
public class ApiVrstaJelaController {

	@Autowired
	private VrstaJelaService vrstaJelaService;
	
	@Autowired
	private VrstaJelaToVrstaJelaDTO toDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<VrstaJelaDTO>> getVrsteJela(
			@RequestParam(value = "name", required = false) String name) {

		List<VrstaJela> vrsteJela;
		vrsteJela= vrstaJelaService.findAll();

		return new ResponseEntity<>(toDto.convert(vrsteJela), HttpStatus.OK);
	}
	
}
