package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.controller;

import java.text.ParseException;
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

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.JedinicaMereDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<JedinicaMereDTO> getJedinicuMere(@PathVariable("id") Integer id){
		JedinicaMere jedinicaMere = jedinicaMereServiceInterface.findOneById(id);
		
		if(jedinicaMere == null) {
			return new ResponseEntity<JedinicaMereDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jedinicaMere), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<JedinicaMereDTO> addMagacin(@RequestBody JedinicaMereDTO jedinicaMereDTO){

		JedinicaMere jm = new JedinicaMere();
		jm.setNaziv(jedinicaMereDTO.getNaziv());
		jm.setSkraceniNaziv(jedinicaMereDTO.getSkraceniNaziv());
		
		jm = jedinicaMereServiceInterface.save(jm);
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jm), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<JedinicaMereDTO> updatePreduzece(@RequestBody JedinicaMereDTO jedinicaMereDTO, @PathVariable("id") Integer id) throws ParseException{
		JedinicaMere jedinicaMere = jedinicaMereServiceInterface.findOneById(id);
		if(jedinicaMere == null) {
			return new ResponseEntity<JedinicaMereDTO>(HttpStatus.BAD_REQUEST);
		}
		jedinicaMere.setNaziv(jedinicaMereDTO.getNaziv());
		jedinicaMere.setSkraceniNaziv(jedinicaMereDTO.getSkraceniNaziv());

		jedinicaMere = jedinicaMereServiceInterface.save(jedinicaMere);
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jedinicaMere), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteJedMere(@PathVariable("id") Integer id){
		JedinicaMere jedinicaMere = jedinicaMereServiceInterface.findOneById(id);
		if(jedinicaMere != null) {
			jedinicaMereServiceInterface.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
