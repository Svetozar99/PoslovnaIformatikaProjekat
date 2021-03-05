package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Entity
@Table(name = "roba_usluga")
public class RobaIliUsluga {

	@Id
	@Column(name = "sifra", nullable = false)
	private int sifra;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="popisniDokument")
	private List<StavkePopisa> stavkePopisa = new ArrayList<StavkePopisa>();
}
