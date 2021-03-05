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
@Table(name = "preduzece")
public class Preduzece {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_preduzeca", nullable = false, unique = true)
	private int idPreduzeca;
	
	@Column(name = "naziv_preduzeca", nullable = false)
	private String nazivPreduzeca;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "telefon", nullable = false)
	private String telefon;
	
	@Column(name = "PIB", nullable = false, unique = true)
	private int PIB;
	
	@Column(name = "MIB", nullable = false)
	private int MIB;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PoslovnaGodina> poslovneGodine = new ArrayList<PoslovnaGodina>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<Magacin> magacini = new ArrayList<Magacin>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="preduzece")
	private List<PrometniDokument> prometniDokument = new ArrayList<PrometniDokument>();
	
}
