package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model.RobaIliUsluga;

public interface RobaIliUslugaRepository extends JpaRepository<RobaIliUsluga, Integer>{

	RobaIliUsluga findOneBySifra(Integer Id);
}
