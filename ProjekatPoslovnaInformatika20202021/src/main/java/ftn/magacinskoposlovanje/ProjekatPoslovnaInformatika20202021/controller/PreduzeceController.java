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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/preduzece")
public class PreduzeceController {

	@Autowired
	private PreduzeceServiceInterface preduzeceService;
	
	@GetMapping
	public ResponseEntity<List<PreduzeceDTO>> getPreduzeca(){
		List<Preduzece> preduzeca = preduzeceService.findAll();
		
		List<PreduzeceDTO> preduzeceDTOs = new ArrayList<PreduzeceDTO>();
		for(Preduzece p: preduzeca) {
			preduzeceDTOs.add(new PreduzeceDTO(p));
		}
		return new ResponseEntity<List<PreduzeceDTO>>(preduzeceDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PreduzeceDTO> getPreduzece(@PathVariable("id") Integer id){
		Preduzece preduzece = preduzeceService.findOne(id);
		
		if(preduzece == null) {
			return new ResponseEntity<PreduzeceDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PreduzeceDTO>(new PreduzeceDTO(preduzece), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PreduzeceDTO> addPreduzece(@RequestBody PreduzeceDTO preduzeceDTO){
		Preduzece preduzece = new Preduzece();
		preduzece.setNazivPreduzeca(preduzeceDTO.getNaziv());
		preduzece.setAdresa(preduzeceDTO.getAdresa());
		preduzece.setTelefon(preduzeceDTO.getTelefon());
		preduzece.setPIB(preduzeceDTO.getpIB());
		preduzece.setMIB(preduzeceDTO.getmIB());
		
		preduzece = preduzeceService.save(preduzece);
		return new ResponseEntity<PreduzeceDTO>(new PreduzeceDTO(preduzece), HttpStatus.CREATED);
	}
}
