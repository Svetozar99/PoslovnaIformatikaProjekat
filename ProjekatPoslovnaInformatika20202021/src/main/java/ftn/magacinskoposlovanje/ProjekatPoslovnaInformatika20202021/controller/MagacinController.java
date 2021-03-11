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
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/magacin")
public class MagacinController {
	
	@Autowired
	MagacinServiceInterface magacinServiceInterface;
	
	@Autowired
	PreduzeceServiceInterface preduzeceServiceInterface;
	
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
	public ResponseEntity<MagacinDTO> getMagacin(@PathVariable("id") Integer id){
		Magacin magacin = magacinServiceInterface.findOne(id);
		
		if(magacin == null) {
			return new ResponseEntity<MagacinDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MagacinDTO>(new MagacinDTO(magacin), HttpStatus.OK);
	}
	
	@GetMapping(value = "preduzece/{id}")
	public ResponseEntity<List<MagacinDTO>> getMagaciniByPreduzece(@PathVariable("id") Integer id){
		List<Magacin> magacini = magacinServiceInterface.findByPreduzece_id(id);
		List<MagacinDTO> dtos = new ArrayList<MagacinDTO>();
		for (Magacin m : magacini) {
			MagacinDTO dto = new MagacinDTO(m);
			dtos.add(dto);
		}
		return new ResponseEntity<List<MagacinDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MagacinDTO> addMagacin(@RequestBody MagacinDTO magacinDTO){
		Preduzece p = preduzeceServiceInterface.findById(magacinDTO.getPreduzece());
		Magacin m = new Magacin();
		m.setNazivMagacina(magacinDTO.getNaziv());	
		m.setPreduzece(p);
		
		m = magacinServiceInterface.save(m);
		return new ResponseEntity<MagacinDTO>(new MagacinDTO(m), HttpStatus.CREATED);
	}

}
