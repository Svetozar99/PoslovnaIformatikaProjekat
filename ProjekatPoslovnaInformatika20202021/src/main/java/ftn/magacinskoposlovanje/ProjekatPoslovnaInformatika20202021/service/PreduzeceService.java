package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PreduzeceServiceInterface;

@Service
public class PreduzeceService implements PreduzeceServiceInterface {

	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Override
	public List<Preduzece> findAll() {
		return preduzeceRepository.findAll();
	}

	@Override
	public Preduzece findOne(Integer preduzeceId) {
		return preduzeceRepository.getOne(preduzeceId);
	}

	@Override
	public Preduzece save(Preduzece preduzece) {
		return preduzeceRepository.save(preduzece);
	}

	@Override
	public void remove(Integer id) {
		preduzeceRepository.deleteById(id);
	}

	@Override
	public Preduzece findById(Integer preduzeceId) {
		return preduzeceRepository.findOneByIdPreduzeca(preduzeceId);
	}

}
