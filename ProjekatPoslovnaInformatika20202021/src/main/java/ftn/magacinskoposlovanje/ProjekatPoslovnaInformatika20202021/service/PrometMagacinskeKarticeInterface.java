package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PrometMagacinskeKartice;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PrometMagacinskeKarticeRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PrometMagacinskeKarticeServiceInterface;

@Service
public class PrometMagacinskeKarticeInterface implements PrometMagacinskeKarticeServiceInterface {

	@Autowired
	PrometMagacinskeKarticeRepository prometMagacinskeKarticeRepository;
	
	@Override
	public List<PrometMagacinskeKartice> findAll() {
		return prometMagacinskeKarticeRepository.findAll();
	}

	@Override
	public PrometMagacinskeKartice findOne(String redniBroj) {
		return prometMagacinskeKarticeRepository.findOneByRedniBroj(redniBroj);
	}

	@Override
	public PrometMagacinskeKartice save(PrometMagacinskeKartice prometMagacinskeKartice) {
		return prometMagacinskeKarticeRepository.save(prometMagacinskeKartice);
	}

	@Override
	public void remove(Integer id) {
		prometMagacinskeKarticeRepository.deleteById(id);
	}

	@Override
	public List<PrometMagacinskeKartice> findByMagacinskaKartica(Integer id) {
		return prometMagacinskeKarticeRepository.findByMagacinskaKartica_id(id);
	}

	@Override
	public List<PrometMagacinskeKartice> findByRedniBroj(String redniBroj) {
		return prometMagacinskeKarticeRepository.findByRedniBroj(redniBroj);
	}

}
