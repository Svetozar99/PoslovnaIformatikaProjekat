package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "promet_magacinske_kartice")
public class PrometMagacinskeKartice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prometa", nullable = false, unique = true)
	private int idPrometa;
	
	@Column(name = "vrsta_prometa", nullable = false)
	private String vrstaPrometa;//mozda treba enumeracija
	
	@Column(name = "smer", nullable = false)
	private String smer;//i ovdje isto
	
	@Column(name = "kolicina", nullable = false)
	private double kolicina;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@Column(name = "vrednost", nullable = false)
	private double vrednost;
	
	@Column(name = "dokument", nullable = false)
	private String dokument;
	
	@Column(name = "datum_prometa", nullable = false)
	private Date datumPrometa;
	
	@ManyToOne
	@JoinColumn(name="magacinska_kartica", referencedColumnName="id", nullable=false)
	private MagacinskaKartica magacinskaKartica;
}
