package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;

public interface PoslovnaGodinaServiceInterface {

	public List<PoslovnaGodina> findAll();
	
	public PoslovnaGodina findOne(Integer id);
	
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
	
	public void remove(Integer id);
	
	public List<PoslovnaGodina> findByPreduzece_id(Integer id);
	
	public PoslovnaGodina findByBrojGodine(Integer brojGodine);
}
