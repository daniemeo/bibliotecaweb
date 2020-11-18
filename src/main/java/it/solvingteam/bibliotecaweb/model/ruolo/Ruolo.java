package it.solvingteam.bibliotecaweb.model.ruolo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Enumerated(EnumType.STRING)
	private CodiceRuolo codice;
	@Column(name = "descrizione")
	private String descrizione;
	
	public Ruolo() {}
	
	public Ruolo(CodiceRuolo codice, String descrizione) {
		this.codice= codice;
		this.descrizione = descrizione;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CodiceRuolo getCodice() {
		return codice;
	}
	public void setCodice(CodiceRuolo codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	@Override
	public String toString() {
		return "Ruolo [id=" + id +", descrizione=" + descrizione +"]";
	}




}
