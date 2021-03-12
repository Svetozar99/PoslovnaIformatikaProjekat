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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovnaGodinaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
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
		List<PoslovnaGodina> pg = poslovnaGodinaServiceInterface.findAll();
		
		List<PoslovnaGodinaDTO> pgDTO = new ArrayList<PoslovnaGodinaDTO>();
		for(PoslovnaGodina p: pg) {
			pgDTO.add(new PoslovnaGodinaDTO(p));
		}
		return new ResponseEntity<List<PoslovnaGodinaDTO>>(pgDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PoslovnaGodinaDTO> getPoslovnaGodina(@PathVariable("id") Integer id){
		PoslovnaGodina pg = poslovnaGodinaServiceInterface.findOne(id);
		
		if(pg == null) {
			return new ResponseEntity<PoslovnaGodinaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(pg), HttpStatus.OK);
	}
	
	@GetMapping(value = "/preduzece/{id}")
	public ResponseEntity<List<PoslovnaGodinaDTO>> getPoslovnaGodinaByPreduzece(@PathVariable("id") Integer id){
		List<PoslovnaGodina> pgs = poslovnaGodinaServiceInterface.findByPreduzece_id(id);
		
		List<PoslovnaGodinaDTO> pgdtos = new ArrayList<PoslovnaGodinaDTO>();
		for(PoslovnaGodina p: pgs) {
			PoslovnaGodinaDTO pdto = new PoslovnaGodinaDTO(p);
			pgdtos.add(pdto);
		}
		return new ResponseEntity<List<PoslovnaGodinaDTO>>(pgdtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PoslovnaGodinaDTO> addPoslovnaGodina(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO){
		Preduzece p = preduzeceServiceInterface.findById(poslovnaGodinaDTO.getPreduzece());
		PoslovnaGodina po = new PoslovnaGodina();
		po.setBrojGodine(poslovnaGodinaDTO.getBrojGodine());
		po.setZakljucena(poslovnaGodinaDTO.getZakljucena());
		po.setPreduzece(p);
		
		po = poslovnaGodinaServiceInterface.save(po);
		return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(po), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PoslovnaGodinaDTO> updatePoslovnaGodina(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO, @PathVariable("id") Integer id) throws ParseException{
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findById(id);
		Preduzece preduzece = preduzeceServiceInterface.findById(poslovnaGodinaDTO.getPreduzece());
		
		if(poslovnaGodina == null) {
			return new ResponseEntity<PoslovnaGodinaDTO>(HttpStatus.BAD_REQUEST);
		}
		poslovnaGodina.setBrojGodine(poslovnaGodinaDTO.getBrojGodine());
		poslovnaGodina.setZakljucena(poslovnaGodinaDTO.getZakljucena());
		poslovnaGodina.setPreduzece(preduzece);
		return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(poslovnaGodina), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePoslovnaGodina(@PathVariable("id") Integer id){
		PoslovnaGodina poslovnaGodina = poslovnaGodinaServiceInterface.findById(id);
		if(poslovnaGodina != null) {
			poslovnaGodinaServiceInterface.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
