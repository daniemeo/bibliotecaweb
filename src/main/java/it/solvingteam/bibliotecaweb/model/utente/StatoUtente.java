package it.solvingteam.bibliotecaweb.model.utente;


public enum StatoUtente {
	NULL,ATTIVO,DISABILITATO;

	@Override 
	public String toString() {
		return (this == NULL ? "" : this.name());
	}
}

