package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.JedinicaMere;

public interface JedinicaMereServiceInterface {

	public List<JedinicaMere> findAll();
	public JedinicaMere findOneById(Integer id);
	public JedinicaMere save(JedinicaMere jedinicaMere);
}
