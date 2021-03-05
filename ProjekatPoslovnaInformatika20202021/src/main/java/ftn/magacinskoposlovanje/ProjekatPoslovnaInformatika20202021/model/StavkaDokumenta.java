package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

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
@Table(name = "stavka_dokumenta")
public class StavkaDokumenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stavke", nullable = false, unique = true)
	public int idStavke;
	
	@Column(name = "kolicina", nullable = false)
	public double kolicina;
	
	@Column(name = "cena", nullable = false)
	public double cena;
	
	@Column(name = "vrednost", nullable = false)
	public double vrednost;

	@ManyToOne
	@JoinColumn(name="magacinska_kartica", referencedColumnName="id", nullable=false)
	private MagacinskaKartica magacinskaKartica;
	
	@ManyToOne
	@JoinColumn(name="prometni_dokument", referencedColumnName="redni_broj", nullable=false)
	private MagacinskaKartica prometniDokument;
	
	@ManyToOne
	@JoinColumn(name="roba_usluga", referencedColumnName="sifra", nullable=false)
	private RobaIliUsluga robaIliUsluga;
}
