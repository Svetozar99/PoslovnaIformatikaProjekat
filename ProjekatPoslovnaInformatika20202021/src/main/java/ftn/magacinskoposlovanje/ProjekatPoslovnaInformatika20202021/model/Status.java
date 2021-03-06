package ftn.magacinskoposlovanje.ProjekatPoslovnaInformatika20202021.model;

public enum Status {
	
	F("U fazi formiranja"),
	P("Proknjizen"),
	S("Storniran");
	
	public final String label;

    private Status(String label) {
        this.label = label;
    }
}
