package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.RobaIliUslugaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@RestController
@RequestMapping(value = "api/roba-ili-usluga")
public class RobaIliUslugaController {

	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@Autowired
	private JedinicaMereServiceInterface jedinicaMereServiceInterface;
	
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
}
