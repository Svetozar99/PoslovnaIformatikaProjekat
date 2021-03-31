package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovniPartnerDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PoslovniPartnerRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository.PreduzeceRepository;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface.PoslovniPartnerServiceInterface;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoslovniPartnerService implements PoslovniPartnerServiceInterface {

	@Autowired
	PoslovniPartnerRepository poslovniPartnerRepository;
	
	@Autowired
	PreduzeceRepository predrepos;
	
	@Override
	public List<PoslovniPartnerDTO> findAll() {
		List<PoslovniPartner> poslovniPartneri = poslovniPartnerRepository.findAll();
		List<PoslovniPartnerDTO> dtos = new ArrayList<PoslovniPartnerDTO>();
		for (PoslovniPartner p : poslovniPartneri) {
			PoslovniPartnerDTO dto = new PoslovniPartnerDTO(p);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public PoslovniPartnerDTO save(PoslovniPartnerDTO dto) {
		Preduzece preduzece = predrepos.findOneByIdPreduzeca(dto.getIdPreduzeca());
		
		PoslovniPartner poslovniPartner = new PoslovniPartner();
		poslovniPartner.setNazivPartnera(dto.getNazivPartnera());
		poslovniPartner.setAdresa(dto.getAdresa());
		poslovniPartner.setBrojTelefona(dto.getBrojTelefona());
		poslovniPartner.setEmail(dto.getEmail());
		poslovniPartner.setPIB(dto.getPIB());
		poslovniPartner.setMIB(dto.getMIB());
		poslovniPartner.setPreduzece(preduzece);
		return new PoslovniPartnerDTO(poslovniPartner);
	}

	@Override
	public PoslovniPartnerDTO update(Integer id, Integer idPreduzeca, PoslovniPartnerDTO pdto) {
		PoslovniPartner poslovniPartner = poslovniPartnerRepository.findOneBySifraPartnera(id);
		Preduzece preduzece = predrepos.findOneByIdPreduzeca(idPreduzeca);
		
		poslovniPartner.setNazivPartnera(pdto.getNazivPartnera());	
		poslovniPartner.setAdresa(pdto.getAdresa());
		poslovniPartner.setBrojTelefona(pdto.getBrojTelefona());	
		poslovniPartner.setEmail(pdto.getEmail());	
		poslovniPartner.setPIB(pdto.getPIB());	
		poslovniPartner.setMIB(pdto.getMIB());	
		poslovniPartner.setPreduzece(preduzece);
		poslovniPartner = poslovniPartnerRepository.save(poslovniPartner);
		PoslovniPartnerDTO pdtoo = new PoslovniPartnerDTO(poslovniPartner);
		return pdtoo;
	}

	@Override
	public void remove(Integer sifraPartnera) {
		poslovniPartnerRepository.deleteById(sifraPartnera);
		
	}

	@Override
	public PoslovniPartnerDTO findOneBySifraPartnera(Integer id) {
		PoslovniPartner pp = poslovniPartnerRepository.findOneBySifraPartnera(id);
		return new PoslovniPartnerDTO(pp);
	}

}
