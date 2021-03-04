package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import javax.persistence.Column;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StavkePopisa {

	@Column(name = "rbr", nullable = false)
	@NonNull private int rbr;
	
	@Column(name = "popisana_kolicina", nullable = false)
	@NonNull private double popisanaKolicina;
}
