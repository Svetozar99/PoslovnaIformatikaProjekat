package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.RobaIliUslugaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.RobaIliUslugaServiceInterface;

@Service
public class RobaIliUslugaService implements RobaIliUslugaServiceInterface{

	@Autowired
	RobaIliUslugaRepository robaIliUslugaRepository;
	
	@Override
	public List<RobaIliUsluga> findAll() {
		return robaIliUslugaRepository.findAll();
	}

	@Override
	public RobaIliUsluga save(RobaIliUsluga robaIliUsluga) {
		return robaIliUslugaRepository.save(robaIliUsluga);
	}

}
