package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.JedinicaMereRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.JedinicaMereServiceInterface;

@Service
public class JedinicaMereService implements JedinicaMereServiceInterface{

	@Autowired
	JedinicaMereRepository jedinicaMereRepository;
	
	@Override
	public List<JedinicaMere> findAll() {
		return jedinicaMereRepository.findAll();
	}

}
