package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.serviceInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.entityDTO.MagacinskaKarticaDTO;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public interface MagacinskaKarticaServiceInterface {

	public List<MagacinskaKarticaDTO> findAll();
	
	public MagacinskaKarticaDTO save(MagacinskaKarticaDTO magacinskaKartica) throws Exception;
	public MagacinskaKarticaDTO findOneById(Integer id);
	public MagacinskaKarticaDTO findOneByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge);
	public MagacinskaKartica findOneByRobaIliUslugaAndPoslovnaGodinaAndMagacin(Integer robaIliUslugaId, Integer poslovnaGodinaId,Integer sifraMagacina) throws Exception;

	List<MagacinskaKarticaDTO> findByRobaIliUsluga_sifraAndPoslovnaGodina_brojGodineAndMagacin_sifraMagacina(Integer sifraRobeUsluge,Integer brojGodine, Integer sifraMagacina);
	List<MagacinskaKarticaDTO> findByMagacin_sifraMagacinaAndRobaIliUsluga_sifra(Integer sifraMagacina,Integer sifraRobeIliUsluge);
	List<MagacinskaKarticaDTO> findByMagacin_sifraMagacinaAndPoslovnaGodina_brojGodine(Integer sifraMagacina,Integer brojGodine);
	List<MagacinskaKarticaDTO> findByPoslovnaGodina_brojGodineAndRobaIliUsluga_sifra(Integer brojGodine,Integer sifraRobeIliUsluge);
	List<MagacinskaKarticaDTO> findByMagacin_sifraMagacina(Integer sifraMagacina);
	List<MagacinskaKarticaDTO> findByPoslovnaGodina_brojGodine(Integer brojGodine);
	List<MagacinskaKarticaDTO> findByRobaIliUsluga_sifra(Integer sifraRobeIliUsluge);
	
	MagacinskaKarticaDTO nivelacija(MagacinskaKarticaDTO dto);
	
	public ResponseEntity report(String broj);
}
