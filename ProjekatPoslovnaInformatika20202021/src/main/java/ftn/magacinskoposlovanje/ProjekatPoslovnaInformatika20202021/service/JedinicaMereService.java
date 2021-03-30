package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.JedinicaMereDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.JedinicaMereRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;

@Service
public class JedinicaMereService implements JedinicaMereServiceInterface{

	@Autowired
	JedinicaMereRepository jedinicaMereRepository;
	
	@Override
	public List<JedinicaMereDTO> findAll() {
		List<JedinicaMere> jedinice = jedinicaMereRepository.findAll();
		
		List<JedinicaMereDTO> dtos = new ArrayList<JedinicaMereDTO>();
		for(JedinicaMere j: jedinice) {
			dtos.add(new JedinicaMereDTO(j));
		}
		return dtos;
	}

	@Override
	public JedinicaMereDTO findOneById(Integer id) {
		JedinicaMere jedinicaMere = jedinicaMereRepository.findOneById(id);
		
//		if(jedinicaMere == null) {
////			return new ResponseEntity<JedinicaMereDTO>(HttpStatus.NOT_FOUND);
//		}
		return new JedinicaMereDTO(jedinicaMere);
	}

	@Override
	public JedinicaMereDTO save(JedinicaMereDTO jedinicaMereDTO) {
		JedinicaMere jm = new JedinicaMere();
		jm.setNaziv(jedinicaMereDTO.getNaziv());
		jm.setSkraceniNaziv(jedinicaMereDTO.getSkraceniNaziv());
		
		jm = jedinicaMereRepository.save(jm);
		
		return new JedinicaMereDTO(jm);
	}
	
	@Override
	public void remove(Integer idJediniceMere) {
		
		jedinicaMereRepository.deleteById(idJediniceMere);
	}

	@Override
	public JedinicaMereDTO update(Integer id,JedinicaMereDTO jedinicaMereDTO) {
		
		JedinicaMere jedinicaMere = jedinicaMereRepository.findOneById(id);
		
		jedinicaMere.setNaziv(jedinicaMereDTO.getNaziv());
		jedinicaMere.setSkraceniNaziv(jedinicaMereDTO.getSkraceniNaziv());

		jedinicaMere = jedinicaMereRepository.save(jedinicaMere);
		return new JedinicaMereDTO(jedinicaMere);
	}
}
