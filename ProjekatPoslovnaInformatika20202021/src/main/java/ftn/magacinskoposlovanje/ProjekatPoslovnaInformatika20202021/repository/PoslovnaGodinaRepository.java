package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.PoslovnaGodina;

public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Integer> {
	
	List<PoslovnaGodina> findByPreduzece_idPreduzeca(Integer id);
	
	PoslovnaGodina findOneByBrojGodine(Integer brojGodine);
	
	PoslovnaGodina findOneByIdGodine(Integer id);
}
