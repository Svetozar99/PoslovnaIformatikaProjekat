package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

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
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovniPartnerDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;

@RestController
@RequestMapping(value = "api/poslovni-partner")
public class PoslovniPartnerController {

	@Autowired
	private PoslovniPartnerServiceInterface poslovniPartnerServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<PoslovniPartnerDTO>> getAll(){
		
		return ResponseEntity.ok().body(poslovniPartnerServiceInterface.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PoslovniPartnerDTO> getPoslovniPartner(@PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(poslovniPartnerServiceInterface.findOneBySifraPartnera(id));
	}
	
	@PostMapping
	public ResponseEntity<PoslovniPartnerDTO> addPartner(@RequestBody PoslovniPartnerDTO dto){
		
		
		return ResponseEntity.ok().body(poslovniPartnerServiceInterface.save(dto));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<PoslovniPartnerDTO> updatePoslovniPartner(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO, @PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(poslovniPartnerServiceInterface.update(poslovniPartnerDTO.getIdPreduzeca(), id, poslovniPartnerDTO));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePoslovniPartner(@PathVariable("id") Integer id){
		poslovniPartnerServiceInterface.remove(id);
		return  ResponseEntity.noContent().build();
	}
}
