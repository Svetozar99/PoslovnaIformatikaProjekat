package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Magacin;

public interface MagacinServiceInterface {
	
	public List<Magacin> findAll();
	public Magacin findOne(Integer magacinId);
	public Magacin save(Magacin magacin);
	public void remove(Integer magacinId);
	public List<Magacin> findByPreduzece_id(Integer id);
}
