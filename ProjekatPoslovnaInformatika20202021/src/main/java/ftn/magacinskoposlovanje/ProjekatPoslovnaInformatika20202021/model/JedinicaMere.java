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
@Table(name = "jedinica_mere")
public class JedinicaMere {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_jedinice_mere", nullable = false, unique = true)
	public int id;
	
	@Column(name = "naziv", nullable = false)
	public String naziv;
	
	@Column(name = "skraceni_naziv", nullable = false)
	public String skraceniNaziv;
	
	@OneToMany(cascade={ALL}, fetch=LAZY, mappedBy="jedinica_mere")
	private List<RobaIliUsluga> robeIliUsluge = new ArrayList<RobaIliUsluga>();
	

}
