package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

public enum VrstaPrometa {
	
	PR("Primka"),
	OT("Otpremnica"),
	MM("Medjumagacinski promet"),
	PS("Pocetno stanje"),
	KP("Korekcija po popisu"),
	NI("Nivelacija");
	
	public final String label;

    private VrstaPrometa(String label) {
        this.label = label;
    }
}
