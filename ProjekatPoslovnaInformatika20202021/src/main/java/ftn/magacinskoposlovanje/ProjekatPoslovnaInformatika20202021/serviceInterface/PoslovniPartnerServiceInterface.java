package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.PoslovniPartnerDTO;

public interface PoslovniPartnerServiceInterface {

	List<PoslovniPartnerDTO> findAll();
	PoslovniPartnerDTO save(PoslovniPartnerDTO poslovniPartnerDTO);
	PoslovniPartnerDTO update(Integer id, Integer idPreduzeca, PoslovniPartnerDTO pdto);
	public void remove(Integer sifraPartnera);
	PoslovniPartnerDTO findOneBySifraPartnera(Integer id);
	
}
