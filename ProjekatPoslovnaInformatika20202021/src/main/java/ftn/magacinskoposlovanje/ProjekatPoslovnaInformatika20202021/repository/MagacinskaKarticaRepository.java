package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.MagacinskaKartica;

public interface MagacinskaKarticaRepository extends JpaRepository<MagacinskaKartica, Integer>{

	MagacinskaKartica findOneById(Integer id);
	MagacinskaKartica findOneByRobaIliUsluga_sifraAndPoslovnaGodina_idGodineAndMagacin_sifraMagacina(Integer sifraRobeUsluge,Integer idGodine, Integer sifraMagacina);
}
