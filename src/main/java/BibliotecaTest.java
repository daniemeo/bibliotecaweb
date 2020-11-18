import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import it.solvingteam.bibliotecaweb.dao.EntityManagerUtil;
import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.libro.GenereLibro;
import it.solvingteam.bibliotecaweb.model.libro.Libro;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.StatoUtente;
import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.autore.AutoreService;
import it.solvingteam.bibliotecaweb.service.libro.LibroService;
import it.solvingteam.bibliotecaweb.service.ruolo.RuoloService;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;




public class BibliotecaTest {
	public static void main( String[] args ) throws Exception {
		
		LibroService  libroServiceInstance = MyServiceFactory.getLibroServiceInstance();
		AutoreService autoreServiceInstance = MyServiceFactory.getAutoreServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
	try {
			
		Autore autore = new Autore("carlo", "rossi", LocalDate.of( 1980,05,10 +1));
		Autore autore1 = new Autore("mario","biondi", LocalDate.of(1990, 05, 10 + 1));
		Autore autore2 = new Autore("dino", "ciao", LocalDate.of(1980, 07, 25 + 1));
		
		autoreServiceInstance.inserisciNuovo(autore);
		autoreServiceInstance.inserisciNuovo(autore1);
		autoreServiceInstance.inserisciNuovo(autore2);
		
		 
		 Autore AutoreDaDb = autoreServiceInstance.get(1L);
		 
		
		Libro libro = new Libro("ggg", GenereLibro.AVVENTURA,"molto interessante");
		Libro libro1 = new Libro("sss", GenereLibro.FANTASY, "gg");
		Set<Libro> listaLibri = new HashSet<>();
		listaLibri.add(libro);
		//listaLibri.
		//autore.setLibri(listaLibri);
		//autoreServiceInstance.inserisciNuovo(autore);
		
		
		//libro.setAutore(AutoreDaDb);
		//libro1.setAutore(AutoreDaDb);
		//libroServiceInstance.inserisciNuovo(libro);
		//libroServiceInstance.inserisciNuovo(libro1);
		
		
		
		//Set<Ruolo> listaRuoli = new HashSet<>();
		
		
		//Utente utente = new Utente("daniela", "meogrossi", "dany", "password", StatoUtente.ATTIVO);
		
		
		
		
		 
			
	}catch(Exception e ) {
		e.printStackTrace();
	}finally {
		// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
	    // main
	}
		EntityManagerUtil.shutdown();
	
	}
	
		
}
