package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public interface MagacinskaKarticaServiceInterface {

	public List<MagacinskaKartica> findAll();
	
	public MagacinskaKartica save(MagacinskaKartica magacinskaKartica);
	public MagacinskaKartica findOneById(Integer id);
	public MagacinskaKartica findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge);
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(Integer robaIliUslugaId, Integer poslovnaGodinaId,Integer sifraMagacina);
}
