package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		return ResponseEntity.ok().body(preduzeceService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PreduzeceDTO> getPreduzece(@PathVariable("id") Integer id) throws Exception{
		
		return ResponseEntity.ok().body(preduzeceService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<PreduzeceDTO> addPreduzece(@RequestBody PreduzeceDTO preduzeceDTO){
		
		return ResponseEntity.ok().body(preduzeceService.save(preduzeceDTO));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PreduzeceDTO> updatePreduzece(@RequestBody PreduzeceDTO preduzeceDTO, @PathVariable("id") Integer id) throws Exception{
		
		return ResponseEntity.ok().body(preduzeceService.update(id, preduzeceDTO));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePreduzece(@PathVariable("id") Integer id){
		preduzeceService.remove(id);
		return  ResponseEntity.noContent().build();
	}
}
