package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "prometni_dokument")
public class PrometniDokument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "redni_broj", nullable = false)
	private int redniBroj;
	
	@Column(name = "vrsta_dokumenta", nullable = false)
	private VrstaDokumenta vrstaDokumenta; //prepravio da bude enumeracija
	
	@Column(name = "datum", nullable = false)
	private Date datum;
	
	@Column(name = "status", nullable = false)
	private Status status = Status.F; //prepravio da bude enumeracija
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="prometniDokument")
	private List<StavkaDokumenta> stavkaDokumenta = new ArrayList<StavkaDokumenta>();
	
	@ManyToOne
	@JoinColumn(name="poslovni_partner", referencedColumnName="sifra_partnera", nullable=true)
	private PoslovniPartner poslovniPartner;
	
	@ManyToOne
	@JoinColumn(name="poslovna_godina", referencedColumnName="broj_godine", nullable=false)
	private PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	@JoinColumn(name="roba_ili_usluga", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;
	
	
	@ManyToOne
	@JoinColumn(name="sifra_magacina", referencedColumnName="sifra_magacina", nullable=true)
	private Magacin magacin;
	
	@ManyToOne
	@JoinColumn(name="id_preduzeca", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;
	
	@ManyToOne
	@JoinColumn(name="magacinska_kartica_id", referencedColumnName="id", nullable=false)
	private MagacinskaKartica magacinskaKartica;
}
