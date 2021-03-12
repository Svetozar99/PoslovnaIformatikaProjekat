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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.JedinicaMereDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@RestController
@RequestMapping(value = "api/jedinica-mere")
public class JedinicaMereController {

	@Autowired
	private JedinicaMereServiceInterface jedinicaMereServiceInterface;
	
	@GetMapping
	public ResponseEntity<List<JedinicaMereDTO>> getJediniceMere(){
		List<JedinicaMere> jedinice = jedinicaMereServiceInterface.findAll();
		
		List<JedinicaMereDTO> dtos = new ArrayList<JedinicaMereDTO>();
		for(JedinicaMere j: jedinice) {
			dtos.add(new JedinicaMereDTO(j));
		}
		return new ResponseEntity<List<JedinicaMereDTO>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<JedinicaMereDTO> addMagacin(@RequestBody JedinicaMereDTO jedinicaMereDTO){

		JedinicaMere jm = new JedinicaMere();
		jm.setNaziv(jedinicaMereDTO.getNaziv());
		jm.setSkraceniNaziv(jedinicaMereDTO.getSkraceniNaziv());
		
		jm = jedinicaMereServiceInterface.save(jm);
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jm), HttpStatus.CREATED);
	}
}
