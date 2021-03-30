package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import antlr.collections.List;
import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.Preduzece;

public interface PreduzeceRepository extends JpaRepository<Preduzece, Integer> {
	
	Preduzece findOneByIdPreduzeca(Integer preduzeceId); 
	
	Preduzece findOneById(Integer id);

}
