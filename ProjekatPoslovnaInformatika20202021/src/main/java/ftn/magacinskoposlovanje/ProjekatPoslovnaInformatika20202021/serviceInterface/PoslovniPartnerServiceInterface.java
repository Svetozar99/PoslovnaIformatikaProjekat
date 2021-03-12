package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovniPartner;

public interface PoslovniPartnerServiceInterface {

	List<PoslovniPartner> findAll();
	PoslovniPartner save(PoslovniPartner poslovniPartner);
	PoslovniPartner findOneBySifraPartnera(Integer id);
}
