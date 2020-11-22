package it.solvingteam.bibliotecaweb.model.libro;

public enum GenereLibro {
  NULL,FANTASY, HORROR, AVVENTURA, GIALLI, SPORT, NARRATIVA_ROSA ;

	@Override 
	public String toString() {
		return (this == NULL ? "" : this.name());
	}
}
