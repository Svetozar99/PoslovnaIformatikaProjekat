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
@Table(name = "magacin")
public class Magacin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra_magacina", nullable = false, unique = true)
	private int sifraMagacina;
	
	@Column(name = "naziv_magacina", nullable = false)
	private String nazivMagacina;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacin")
	private List<PopisniDokument> popisniDokumenti = new ArrayList<PopisniDokument>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacin")
	private List<MagacinskaKartica> magacinskeKartice = new ArrayList<MagacinskaKartica>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacin")
	private List<PrometniDokument> prometniDokumenti = new ArrayList<PrometniDokument>();
	
	
	@ManyToOne
	@JoinColumn(name="preduzece", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;
}
