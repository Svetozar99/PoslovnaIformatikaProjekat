package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.MagacinRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.MagacinServiceInterface;

@Service
public class MagacinService implements MagacinServiceInterface{

	@Autowired
	MagacinRepository magacinRepository;
	
	@Override
	public List<Magacin> findAll() {
		return magacinRepository.findAll();
	}

	@Override
	public Magacin findOne(Integer magacinId) {
		return magacinRepository.getOne(magacinId);
	}

	@Override
	public Magacin save(Magacin magacin) {
		return magacinRepository.save(magacin);
	}

	@Override
	public void remove(Integer magacinId) {
		magacinRepository.deleteById(magacinId);
		
	}

	@Override
	public List<Magacin> findByPreduzece_id(Integer id) {
		return magacinRepository.findByPreduzece_idPreduzeca(id);
	}

	@Override
	public Magacin findById(Integer id) {
		return magacinRepository.findOneBySifraMagacina(id);
	}

}
