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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.RobaIliUslugaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@RestController
@RequestMapping(value = "api/roba-ili-usluga")
public class RobaIliUslugaController {

	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<RobaIliUslugaDTO>> getRobeIliUsluge(){
		
		return ResponseEntity.ok().body(robaIliUslugaServiceInterface.findAll());
	}
	
	@GetMapping(value = "/{sifraMagacina}")
	public ResponseEntity<List<RobaIliUslugaDTO>> getRobeIliUslugeByMagacin(@PathVariable("sifraMagacina") Integer sifraMagacina){
		
		return ResponseEntity.ok().body(robaIliUslugaServiceInterface.findByMagKartica(sifraMagacina));
	}
	
	@GetMapping(value = "jedna-roba-ili-usluga/{id}")
	public ResponseEntity<RobaIliUslugaDTO> getRiliL(@PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(robaIliUslugaServiceInterface.findOneBySifra(id));
	}
	
	@PostMapping
	public ResponseEntity<RobaIliUslugaDTO> addRobaIliUsluga(@RequestBody RobaIliUslugaDTO dto){
		return ResponseEntity.ok().body(robaIliUslugaServiceInterface.save(dto));
	}
	
	@PutMapping(value = "/{sifra}", consumes = "application/json")
	public ResponseEntity<Void> updateRobaIliUsluga(@RequestBody RobaIliUslugaDTO robaIliUslugaDTO, @PathVariable("sifra") Integer sifra){
		try {
			robaIliUslugaServiceInterface.update(sifra,robaIliUslugaDTO);
			return  ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteRobaIliUsluga(@PathVariable("id") Integer id){
		robaIliUslugaServiceInterface.delete(id);
		return  ResponseEntity.noContent().build();
	}
}
