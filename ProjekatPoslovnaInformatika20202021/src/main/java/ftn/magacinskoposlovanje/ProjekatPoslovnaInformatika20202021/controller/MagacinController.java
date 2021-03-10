package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;

@RestController
@RequestMapping(value = "api/magacin")
public class MagacinController {
	
	@Autowired
	MagacinServiceInterface magacinServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<MagacinDTO>> getMagacin(){
		List<Magacin> magacini = magacinServiceInterface.findAll();
		
		List<MagacinDTO> magacinDTO = new ArrayList<MagacinDTO>();
		for(Magacin mag: magacini) {
			magacinDTO.add(new MagacinDTO(mag));
		}
		return new ResponseEntity<List<MagacinDTO>>(magacinDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MagacinDTO> getPreduzece(@PathVariable("id") Integer id){
		Magacin magacin = magacinServiceInterface.findOne(id);
		
		if(magacin == null) {
			return new ResponseEntity<MagacinDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MagacinDTO>(new MagacinDTO(magacin), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MagacinDTO> addMagacin(@RequestBody MagacinDTO magacinDTO){
		Magacin m = new Magacin();
		m.setNazivMagacina(magacinDTO.getNaziv());	
		m.setPreduzece(magacinDTO.getPreduzece());
		
		m = magacinServiceInterface.save(m);
		return new ResponseEntity<MagacinDTO>(new MagacinDTO(m), HttpStatus.CREATED);
	}

}
