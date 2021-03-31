package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovnaGodinaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/poslovna-godina")
public class PoslovnaGodinaController {

	@Autowired
	PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;
	
	@Autowired
	PreduzeceServiceInterface preduzeceServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<PoslovnaGodinaDTO>> getPoslovneGodine(){
		
		return ResponseEntity.ok().body(poslovnaGodinaServiceInterface.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PoslovnaGodinaDTO> getPoslovnaGodina(@PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(poslovnaGodinaServiceInterface.findByBrojGodine(id));
	}
	
	@GetMapping(value = "/preduzece/{id}")
	public ResponseEntity<List<PoslovnaGodinaDTO>> getPoslovnaGodinaByPreduzece(@PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(poslovnaGodinaServiceInterface.findByPreduzece_id(id));
	}
	
	@PostMapping
	public ResponseEntity<PoslovnaGodinaDTO> addPoslovnaGodina(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO){
		
		return ResponseEntity.ok().body(poslovnaGodinaServiceInterface.save(poslovnaGodinaDTO));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<Void> updatePoslovnaGodina(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO, @PathVariable("id") Integer id) throws ParseException{
		try {
			poslovnaGodinaServiceInterface.update(poslovnaGodinaDTO);
			return  ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePoslovnaGodina(@PathVariable("id") Integer id){
		try {
			poslovnaGodinaServiceInterface.remove(id);
			return  ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
