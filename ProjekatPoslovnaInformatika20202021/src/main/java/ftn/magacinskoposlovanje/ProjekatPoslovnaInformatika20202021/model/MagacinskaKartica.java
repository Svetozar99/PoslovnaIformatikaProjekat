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
@Table(name = "magacinsa_kartica")
public class MagacinskaKartica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "pocetno_stanje_kolicinski", nullable = false)
	private double pocetnoStanjeKolicinski; 
	
	@Column(name = "promet_ulaza_kolicinski", nullable = false)
	private double prometUlazaKolicinski;
	
	@Column(name = "promet_izlaza_kolicinski", nullable = false)
	private double prometIzlazaKolicinski;
	
	@Column(name = "ukupna_kolicina", nullable = false)
	private double ukupnaKolicina;
	
	@Column(name = "pocetno_stanje_vrednosno", nullable = false)
	private double pocetnoStanjeVrednosno;
	
	@Column(name = "promet_ulaza_vrednosno", nullable = false)
	private double prometUlazaVrednosno;
	
	@Column(name = "promet_izlaza_vrednosno", nullable = false)
	private double prometIzlazaVrednosno;
	
	@Column(name = "ukupna_vrednost", nullable = false)
	private double ukupnaVrednost;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@ManyToOne
	@JoinColumn(name="magacin", referencedColumnName="sifra_magacina", nullable=false)
	private Magacin magacin;
	
	@ManyToOne
	@JoinColumn(name="poslovna_godina", referencedColumnName="broj_godine", nullable=false)
	private PoslovnaGodina poslovnaGodina;
	
	@ManyToOne
	@JoinColumn(name="roba_ili_usluga", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;

	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacinskaKartica")
	private List<PrometMagacinskeKartice> prometMagacinskeKartice = new ArrayList<PrometMagacinskeKartice>();
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="magacinskaKartica")
	private List<StavkaDokumenta> stavkaDokumenta = new ArrayList<StavkaDokumenta>();
	
}
