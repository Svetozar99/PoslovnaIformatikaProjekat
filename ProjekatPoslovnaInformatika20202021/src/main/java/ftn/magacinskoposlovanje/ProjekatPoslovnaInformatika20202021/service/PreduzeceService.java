package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PreduzeceDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@Service
public class PreduzeceService implements PreduzeceServiceInterface {

	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Override
	public List<PreduzeceDTO> findAll() {
		List<Preduzece> preduzeca = preduzeceRepository.findAll();
		
		List<PreduzeceDTO> preduzeceDTOs = new ArrayList<PreduzeceDTO>();
		for(Preduzece p: preduzeca) {
			preduzeceDTOs.add(new PreduzeceDTO(p));
		}
		return preduzeceDTOs;
	}

	@Override
	public PreduzeceDTO findById(Integer preduzeceId) throws Exception {
		Preduzece preduzece = preduzeceRepository.findOneByIdPreduzeca(preduzeceId);
		
		if(preduzece == null) {
			throw new Exception("Ne postoji preduzeće!");
		}
		return new PreduzeceDTO(preduzece);
	}

	@Override
	public PreduzeceDTO save(PreduzeceDTO preduzeceDTO) {
		Preduzece preduzece = new Preduzece();
		preduzece.setNazivPreduzeca(preduzeceDTO.getNaziv());
		preduzece.setAdresa(preduzeceDTO.getAdresa());
		preduzece.setTelefon(preduzeceDTO.getTelefon());
		preduzece.setPIB(preduzeceDTO.getpIB());
		preduzece.setMIB(preduzeceDTO.getmIB());
		
		preduzece = preduzeceRepository.save(preduzece);
		return new PreduzeceDTO(preduzece);
	}

	@Override
	public void remove(Integer id) {
		preduzeceRepository.deleteById(id);
	}

	@Override
	public Preduzece findOne(Integer preduzeceId) {
		return preduzeceRepository.findOneByIdPreduzeca(preduzeceId);
	}

	@Override
	public PreduzeceDTO update(Integer id,PreduzeceDTO preduzeceDTO) throws Exception{
		Preduzece preduzece = preduzeceRepository.findOneByIdPreduzeca(id);
		System.out.println("\n\tPoziva se funkcija POST");
		if(preduzece == null) {
			throw new Exception("Ne postoji preduzeće!");
		}
		preduzece.setNazivPreduzeca(preduzeceDTO.getNaziv());
		preduzece.setAdresa(preduzeceDTO.getAdresa());
		preduzece.setTelefon(preduzeceDTO.getTelefon());
		preduzece.setPIB(preduzeceDTO.getpIB());
		preduzece.setMIB(preduzeceDTO.getmIB());
		preduzece = preduzeceRepository.save(preduzece);
		return new PreduzeceDTO(preduzece);
	}

}
