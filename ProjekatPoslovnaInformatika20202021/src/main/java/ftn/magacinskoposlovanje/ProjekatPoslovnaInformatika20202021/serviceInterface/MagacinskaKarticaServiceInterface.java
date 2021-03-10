package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public interface MagacinskaKarticaServiceInterface {

	public List<MagacinskaKartica> findAll();
	
	public MagacinskaKartica save(MagacinskaKartica magacinskaKartica);
}