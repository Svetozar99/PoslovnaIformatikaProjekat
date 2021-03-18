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
	
	@GetMapping(value = "{sifraMagacina}/{sifraRobeIliUsluge}/{brojPoslovneGodine}")
	public ResponseEntity<List<MagacinskaKarticaDTO>> getMagKartByRobaIliUslua(@PathVariable("sifraMagacina") Integer sifraMagacina, @PathVariable("sifraRobeIliUsluge") Integer sifraRobeIliUsluge, 
			@PathVariable("brojPoslovneGodine") Integer brojPoslovneGodine){
		List<MagacinskaKartica> kartice = new ArrayList<MagacinskaKartica>();
		List<MagacinskaKarticaDTO> dtos = new ArrayList<MagacinskaKarticaDTO>();
		if(sifraMagacina==0 && sifraRobeIliUsluge==0 && brojPoslovneGodine==0) {
			kartice = magaKarticaServiceInterface.findAll();
		}else if(sifraMagacina!=0 && sifraRobeIliUsluge!=0 && brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(sifraRobeIliUsluge, brojPoslovneGodine, sifraMagacina);
		}else if(sifraMagacina!=0 && sifraRobeIliUsluge!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(sifraMagacina, sifraRobeIliUsluge);
		}else if(sifraRobeIliUsluge!=0 && brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(brojPoslovneGodine, sifraRobeIliUsluge);
		}else if(sifraMagacina!=0 &&brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(sifraMagacina, brojPoslovneGodine);
		}else if(sifraMagacina!=0) {
			kartice = magaKarticaServiceInterface.findByMagacin_sifraMagacina(sifraMagacina);
		}
		else if(sifraRobeIliUsluge!=0) {
			kartice = magaKarticaServiceInterface.findByRobaIliUsluga_sifra(sifraRobeIliUsluge);
		}
		else if(brojPoslovneGodine!=0) {
			kartice = magaKarticaServiceInterface.findByPoslovnaGodina_brojGodine(brojPoslovneGodine);
		}
		for (MagacinskaKartica k : kartice) {
			dtos.add(new MagacinskaKarticaDTO(k));
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MagacinskaKarticaDTO> getOneMagKar(@PathVariable("id") Integer id){
		
		MagacinskaKartica mk = magaKarticaServiceInterface.findOneById(id);
		
		return new ResponseEntity<MagacinskaKarticaDTO>(new MagacinskaKarticaDTO(mk),HttpStatus.OK);
	}
}
