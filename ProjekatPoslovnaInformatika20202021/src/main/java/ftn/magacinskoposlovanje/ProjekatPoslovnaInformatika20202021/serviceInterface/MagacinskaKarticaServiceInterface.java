package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public interface MagacinskaKarticaServiceInterface {

	public List<MagacinskaKartica> findAll();
	
	public MagacinskaKartica save(MagacinskaKartica magacinskaKartica);
	public MagacinskaKartica findOneById(Integer id);
	public MagacinskaKartica findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge);
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(Integer robaIliUslugaId, Integer poslovnaGodinaId,Integer sifraMagacina) throws Exception;

	List<MagacinskaKartica> findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(Integer sifraRobeUsluge,Integer brojGodine, Integer sifraMagacina);
	List<MagacinskaKartica> findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge);
	List<MagacinskaKartica> findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(Integer sifraMagacina,Integer brojGodine);
	List<MagacinskaKartica> findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(Integer brojGodine,Integer sifraRobeIliUsluge);
	List<MagacinskaKartica> findByMagacin_sifraMagacina(Integer sifraMagacina);
	List<MagacinskaKartica> findByPoslovnaGodina_brojGodine(Integer brojGodine);
	List<MagacinskaKartica> findByRobaIliUsluga_sifra(Integer sifraRobeIliUsluge);
}
