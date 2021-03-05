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
@Table(name = "poslovni_partner")
public class PoslovniPartner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sifra_partnera", nullable = false)
	private int sifraPartnera;
	
	@Column(name = "naziv_partnera", nullable = false)
	private String nazivPartnera;
	
	@Column(name = "adresa_preduzeca", nullable = false)
	private String adresa;
	
	@Column(name = "broj_telefona", nullable = false)
	private String brojTelefona;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "PIB", nullable = false, unique = true)
	private int PIB;
	
	@Column(name = "MIB", nullable = false)
	private int MIB;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="poslovniPartner")
	private List<PrometniDokument> prometniDokumenti = new ArrayList<PrometniDokument>();
	
	@ManyToOne
	@JoinColumn(name="preduzece", referencedColumnName="id_preduzeca", nullable=false)
	private Preduzece preduzece;

}
