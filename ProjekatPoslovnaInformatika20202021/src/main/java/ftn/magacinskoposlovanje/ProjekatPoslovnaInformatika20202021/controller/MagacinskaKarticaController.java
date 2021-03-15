package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinskaKarticaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinskaKarticaServiceInterface;

@RestController
@RequestMapping(value = "api/magacinska-kartica")
public class MagacinskaKarticaController {
	
	@Autowired
	private MagacinskaKarticaServiceInterface magaKarticaServiceInterface;

	@GetMapping
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagacinskeKartice(){
		List<MagacinskaKartica> magacinskaKarticas = magaKarticaServiceInterface.findAll();
		
		List<MagacinskaKarticaDTO> karicaDTOs = new ArrayList<MagacinskaKarticaDTO>();
		for(MagacinskaKartica m : magacinskaKarticas) {
			karicaDTOs.add(new MagacinskaKarticaDTO(m));
		}
		return new ResponseEntity<List<MagacinskaKarticaDTO>>(karicaDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "roba-ili-usluga/{idRobeIliUsluge}/poslovna-godina/{idPoslovneGodine}/magacin/{idMagacina}")
	public ResponseEntity<MagacinskaKarticaDTO> getMagKartByRobaIliUslua(@PathVariable("idRobeIliUsluge") Integer idRobeIliUsluge, 
			@PathVariable("idPoslovneGodine") Integer idPoslovneGodine, @PathVariable("idMagacina") Integer idMagacina){
		MagacinskaKartica magacinskaKartica = magaKarticaServiceInterface.findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(idRobeIliUsluge, idPoslovneGodine,idMagacina);
		return ResponseEntity.ok(new MagacinskaKarticaDTO(magacinskaKartica));
	}
}
