package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovniPartnerRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;

@Service
public class PoslovniPartnerService implements PoslovniPartnerServiceInterface {

	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepository;
	
	@Override
	public List<PoslovniPartner> findAll() {
		return poslovniPartnerRepository.findAll();
	}

	@Override
	public PoslovniPartner save(PoslovniPartner poslovniPartner) {
		return poslovniPartnerRepository.save(poslovniPartner);
	}

	@Override
	public PoslovniPartner findOneBySifraPartnera(Integer id) {
		return poslovniPartnerRepository.findOneBySifraPartnera(id);
	}

}
