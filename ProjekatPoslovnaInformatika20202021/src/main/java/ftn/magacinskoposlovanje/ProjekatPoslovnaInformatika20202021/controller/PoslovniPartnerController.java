package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovniPartnerDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/poslovni-partner")
public class PoslovniPartnerController {

	@Autowired
	private PoslovniPartnerServiceInterface poslovniPartnerServiceInterface;
	
	@Autowired
	private PreduzeceServiceInterface preduzeceServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<PoslovniPartnerDTO>> getAll(){
		List<PoslovniPartner> poslovniPartneri = poslovniPartnerServiceInterface.findAll();
		List<PoslovniPartnerDTO> dtos = new ArrayList<PoslovniPartnerDTO>();
		for (PoslovniPartner p : poslovniPartneri) {
			PoslovniPartnerDTO dto = new PoslovniPartnerDTO(p);
			dtos.add(dto);
		}
		return new ResponseEntity<List<PoslovniPartnerDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PoslovniPartnerDTO> getPoslovniPartner(@PathVariable("id") Integer id){
		PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(id);
		
		if(poslovniPartner == null) {
			return new ResponseEntity<PoslovniPartnerDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PoslovniPartnerDTO>(new PoslovniPartnerDTO(poslovniPartner), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PoslovniPartnerDTO> addPartner(@RequestBody PoslovniPartnerDTO dto){
		Preduzece preduzece = preduzeceServiceInterface.findById(dto.getIdPreduzeca());
		
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		poslovniPartner.setNazivPartnera(dto.getNazivPartnera());
		poslovniPartner.setAdresa(dto.getAdresa());
		poslovniPartner.setBrojTelefona(dto.getBrojTelefona());
		poslovniPartner.setEmail(dto.getEmail());
		poslovniPartner.setPIB(dto.getPIB());
		poslovniPartner.setMIB(dto.getMIB());
		poslovniPartner.setPreduzece(preduzece);
		
		return new ResponseEntity<PoslovniPartnerDTO>(new PoslovniPartnerDTO(poslovniPartnerServiceInterface.save(poslovniPartner)), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PoslovniPartnerDTO> updatePoslovniPartner(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO, @PathVariable("id") Integer id){
		PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(id);
		Preduzece preduzece = preduzeceServiceInterface.findById(poslovniPartnerDTO.getIdPreduzeca());
		
		if(poslovniPartner == null) {
			return new ResponseEntity<PoslovniPartnerDTO>(HttpStatus.BAD_REQUEST);
		}

		poslovniPartner.setNazivPartnera(poslovniPartnerDTO.getNazivPartnera());	
		poslovniPartner.setAdresa(poslovniPartnerDTO.getAdresa());
		poslovniPartner.setBrojTelefona(poslovniPartnerDTO.getBrojTelefona());	
		poslovniPartner.setEmail(poslovniPartnerDTO.getEmail());	
		poslovniPartner.setPIB(poslovniPartnerDTO.getPIB());	
		poslovniPartner.setMIB(poslovniPartnerDTO.getMIB());	
		poslovniPartner.setPreduzece(preduzece);
		poslovniPartner = poslovniPartnerServiceInterface.save(poslovniPartner);
		return new ResponseEntity<PoslovniPartnerDTO>(new PoslovniPartnerDTO(poslovniPartner), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePoslovniPartner(@PathVariable("id") Integer id){
		PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOneBySifraPartnera(id);
		if(poslovniPartner != null) {
			poslovniPartnerServiceInterface.remove(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
