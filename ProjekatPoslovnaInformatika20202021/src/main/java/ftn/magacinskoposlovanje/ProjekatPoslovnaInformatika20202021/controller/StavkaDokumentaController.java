package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.StavkaDokumentaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometniDokument;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.StavkaDokumenta;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometniDokumentServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.StavkaDokumentaServiceInterface;

@RestController
@RequestMapping(value = "api/stavka-dokumenta")
public class StavkaDokumentaController {

	@Autowired
	private StavkaDokumentaServiceInterface stavkaDokumentaServiceInterface;
	
	@Autowired
	private PrometniDokumentServiceInterface prometniDokumentServiceInterface;
	
	@Autowired
	private RobaIliUslugaServiceInterface robaIliUslugaServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<StavkaDokumentaDTO>> getStavkeDokumenta(){
		List<StavkaDokumenta> stavkaDokumentas = stavkaDokumentaServiceInterface.findAll();
		
		List<StavkaDokumentaDTO> stavkaDokumentaDTO = new ArrayList<StavkaDokumentaDTO>();
		for(StavkaDokumenta std: stavkaDokumentas) {
			stavkaDokumentaDTO.add(new StavkaDokumentaDTO(std));
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(stavkaDokumentaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StavkaDokumentaDTO> getStavkaDokumenta(@PathVariable("id") Integer id){
		StavkaDokumenta stavkaDokumenta = stavkaDokumentaServiceInterface.findById(id);
		
		if(stavkaDokumenta == null) {
			return new ResponseEntity<StavkaDokumentaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StavkaDokumentaDTO>(new StavkaDokumentaDTO(stavkaDokumenta), HttpStatus.OK);
	}
	
	@GetMapping(value = "prometni-dokumet/{id}")
	public ResponseEntity<List<StavkaDokumentaDTO>> getstavkeDocByPromDoc(@PathVariable("id") Integer id){
		List<StavkaDokumenta> stavkeDokumenta = stavkaDokumentaServiceInterface.findByPrometniDokument_id(id);
		List<StavkaDokumentaDTO> sdtos = new ArrayList<StavkaDokumentaDTO>();
		for (StavkaDokumenta sdto : stavkeDokumenta) {
			StavkaDokumentaDTO s = new StavkaDokumentaDTO(sdto);
			sdtos.add(s);
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(sdtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "roba-ili-usluga/{id}")
	public ResponseEntity<List<StavkaDokumentaDTO>> getStavkeDocByRobaIliUsluga(@PathVariable("id") Integer id){
		List<StavkaDokumenta> stavkeDokumenta = stavkaDokumentaServiceInterface.findByRobaIliUsluga_sifra(id);
		List<StavkaDokumentaDTO> sdtos = new ArrayList<StavkaDokumentaDTO>();
		for (StavkaDokumenta sdto : stavkeDokumenta) {
			StavkaDokumentaDTO s = new StavkaDokumentaDTO(sdto);
			sdtos.add(s);
		}
		return new ResponseEntity<List<StavkaDokumentaDTO>>(sdtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StavkaDokumentaDTO> addMagacin(@RequestBody StavkaDokumentaDTO stavkaDokumentaDTO){
		PrometniDokument pd = prometniDokumentServiceInterface.findOneById(stavkaDokumentaDTO.getPrometniDokument());
		RobaIliUsluga ru = robaIliUslugaServiceInterface.findOneBySifra(stavkaDokumentaDTO.getRobaUsluga());
		StavkaDokumenta stavkaDokumenta = new StavkaDokumenta();
		
		stavkaDokumenta.setKolicina(stavkaDokumentaDTO.getKolicina());
		stavkaDokumenta.setCena(stavkaDokumentaDTO.getCena());
		stavkaDokumenta.setVrednost(stavkaDokumentaDTO.getVrednost());
		stavkaDokumenta.setPrometniDokument(pd);
		stavkaDokumenta.setRobaIliUsluga(ru);
		
		stavkaDokumenta = stavkaDokumentaServiceInterface.save(stavkaDokumenta);
		return new ResponseEntity<StavkaDokumentaDTO>(new StavkaDokumentaDTO(stavkaDokumenta), HttpStatus.CREATED);
	}
}
