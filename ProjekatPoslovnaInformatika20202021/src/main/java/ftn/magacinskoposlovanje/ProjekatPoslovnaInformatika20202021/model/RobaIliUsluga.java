package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "roba_usluga")
public class RobaIliUsluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra", nullable = false, unique = true)
	private int sifra;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="popisniDokument")
	private List<StavkePopisa> stavkePopisa = new ArrayList<StavkePopisa>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacin")
	private List<MagacinskaKartica> magacinskeKartice = new ArrayList<MagacinskaKartica>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="robaIliUsluga")
	private List<PrometniDokument> prometniDokumenti = new ArrayList<PrometniDokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="robaIliUsluga")
	private List<StavkaDokumenta> stavkeDokumenta = new ArrayList<StavkaDokumenta>();
	
	@ManyToOne
	@JoinColumn(name="jedinica_mere", referencedColumnName="id_jedinice_mere", nullable=false)
	private JedinicaMere jedinicaMere;
}
