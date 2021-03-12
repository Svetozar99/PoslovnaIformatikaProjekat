package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovnaGodinaRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovnaGodinaServiceInterface;

@Service
public class PoslovnaGodinaInterface implements PoslovnaGodinaServiceInterface {

	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	@Override
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodinaRepository.findAll();
	}

	@Override
	public PoslovnaGodina findOne(Integer id) {
		return poslovnaGodinaRepository.getOne(id);
	}

	@Override
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		return poslovnaGodinaRepository.save(poslovnaGodina);
	}

	@Override
	public void remove(Integer id) {
		poslovnaGodinaRepository.deleteById(id);

	}

	@Override
	public List<PoslovnaGodina> findByPreduzece_id(Integer id) {
		return poslovnaGodinaRepository.findByPreduzece_idPreduzeca(id);
	}

	@Override
	public PoslovnaGodina findByBrojGodine(Integer brojBodine) {
		return poslovnaGodinaRepository.findOneByBrojGodine(brojBodine);
	}

	@Override
	public PoslovnaGodina findById(Integer id) {
		return poslovnaGodinaRepository.findOneByIdGodine(id);
	}

}
