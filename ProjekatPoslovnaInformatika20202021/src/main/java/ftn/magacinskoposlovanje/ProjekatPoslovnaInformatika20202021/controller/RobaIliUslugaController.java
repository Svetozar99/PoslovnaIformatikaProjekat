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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.RobaIliUslugaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@RestController
@RequestMapping(value = "api/roba-ili-usluga")
public class RobaIliUslugaController {

	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@Autowired
	private JedinicaMereServiceInterface jedinicaMereServiceInterface;
	
	@Autowired
	private MagacinskaKarticaServiceInterface magacinskaKarticaServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<RobaIliUslugaDTO>> getRobeIliUsluge(){
		List<RobaIliUsluga> robaIliUsluga = robaIliUslugaServiceInterface.findAll();
		
		List<RobaIliUslugaDTO> dtos = new ArrayList<RobaIliUslugaDTO>();
		for(RobaIliUsluga ru: robaIliUsluga) {
			RobaIliUslugaDTO dto = new RobaIliUslugaDTO();
			dto.setSifra(ru.getSifra());
			dto.setNaziv(ru.getNaziv());
			dto.setJedinicaMere(ru.getJedinicaMere().getSkraceniNaziv());
			dtos.add(dto);
		}
		return new ResponseEntity<List<RobaIliUslugaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{sifraMagacina}")
	public ResponseEntity<List<RobaIliUslugaDTO>> getRobeIliUslugeByMagacin(@PathVariable("sifraMagacina") Integer sifraMagacina){
		List<RobaIliUsluga> robaIliUsluga = robaIliUslugaServiceInterface.findAll();
		
		List<RobaIliUslugaDTO> dtos = new ArrayList<RobaIliUslugaDTO>();
		for(RobaIliUsluga ru: robaIliUsluga) {
			MagacinskaKartica kartica = magacinskaKarticaServiceInterface.findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina,ru.getSifra());
			RobaIliUslugaDTO dto = new RobaIliUslugaDTO();
			dto.setSifra(ru.getSifra());
			dto.setNaziv(ru.getNaziv());
			dto.setJedinicaMere(ru.getJedinicaMere().getSkraceniNaziv());
			if(kartica != null) {
				dto.setCena(kartica.getCena());
				dtos.add(dto);
			}
		}
		return new ResponseEntity<List<RobaIliUslugaDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "jedna-roba-ili-usluga/{id}")
	public ResponseEntity<RobaIliUslugaDTO> getRiliL(@PathVariable("id") Integer id){
		RobaIliUsluga rl = robaIliUslugaServiceInterface.getOne(id);
		
		if(rl == null) {
			return new ResponseEntity<RobaIliUslugaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RobaIliUslugaDTO>(new RobaIliUslugaDTO(rl), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<RobaIliUslugaDTO> addRobaIliUsluga(@RequestBody RobaIliUslugaDTO dto){
		JedinicaMere jedinicaMere = jedinicaMereServiceInterface.findOneById(dto.getIdJedinicaMere());

		RobaIliUsluga robaIliUsluga = new RobaIliUsluga();
		robaIliUsluga.setNaziv(dto.getNaziv());
		robaIliUsluga.setJedinicaMere(jedinicaMere);
		
		robaIliUsluga = robaIliUslugaServiceInterface.save(robaIliUsluga);
		dto.setSifra(robaIliUsluga.getSifra());
		dto.setJedinicaMere(robaIliUsluga.getJedinicaMere().getSkraceniNaziv());
		return new ResponseEntity<RobaIliUslugaDTO>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{sifra}", consumes = "application/json")
	public ResponseEntity<RobaIliUslugaDTO> updateRobaIliUsluga(@RequestBody RobaIliUslugaDTO robaIliUslugaDTO, @PathVariable("sifra") Integer sifra){
		RobaIliUsluga rilil = robaIliUslugaServiceInterface.findOneBySifra(sifra);
		JedinicaMere jm = jedinicaMereServiceInterface.findOneById(robaIliUslugaDTO.getIdJedinicaMere());
		
		if(rilil == null) {
			return new ResponseEntity<RobaIliUslugaDTO>(HttpStatus.BAD_REQUEST);
		}
		rilil.setNaziv(robaIliUslugaDTO.getNaziv());	
		rilil.setJedinicaMere(jm);
		rilil.setCena(robaIliUslugaDTO.getCena());
		rilil = robaIliUslugaServiceInterface.save(rilil);
		return new ResponseEntity<RobaIliUslugaDTO>(new RobaIliUslugaDTO(rilil), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteRobaIliUsluga(@PathVariable("id") Integer id){
		RobaIliUsluga r = robaIliUslugaServiceInterface.findOneBySifra(id);
		if(r != null) {
			robaIliUslugaServiceInterface.delete(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
