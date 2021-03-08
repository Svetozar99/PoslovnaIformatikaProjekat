package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;

public interface RobaIliUslugaServiceInterface {

	public List<RobaIliUsluga> findAll();
	
	public RobaIliUsluga save(RobaIliUsluga robaIliUsluga);
}
