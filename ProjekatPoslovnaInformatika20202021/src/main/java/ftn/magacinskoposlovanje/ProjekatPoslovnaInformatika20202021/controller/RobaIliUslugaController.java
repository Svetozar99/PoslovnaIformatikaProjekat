package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.RobaIliUslugaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@RestController
@RequestMapping(value = "api/roba-ili-usluga")
public class RobaIliUslugaController {

	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<RobaIliUslugaDTO>> getPreduzeca(){
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
}
