package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PrometMagacinskeKarticeDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;

@RestController
@RequestMapping(value = "api/promet-magacinske-kartice")
public class PrometMagacinskeKatriceController {
	
	@Autowired
	private PrometMagacinskeKarticeServiceInterface prometMagacinskeKarticeService;
	
	@GetMapping
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKart(){
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findAll());
	}
	
	@GetMapping(value = "/{idKartice}")
	public ResponseEntity<List<PrometMagacinskeKarticeDTO>> getPrometiMagKartByKartica(@PathVariable ("idKartice") Integer idKartice){
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findByMagacinskaKartica(idKartice));
	}
	
	@GetMapping(value = "storniraj/{redniBroj}")
	public ResponseEntity<PrometMagacinskeKarticeDTO> getOnePromet(@PathVariable("redniBroj") String redniBroj) throws Exception{
		
		return ResponseEntity.ok().body(prometMagacinskeKarticeService.findByRedniBroj(redniBroj));
	}
	
	
}
