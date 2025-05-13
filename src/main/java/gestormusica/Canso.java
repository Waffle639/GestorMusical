package gestormusica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import mypackage.PlaylisFts;

/**
 * Classe que emmagatzema les dades d'una canço individual
 * 
 * @author Juan Martín
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Canso", propOrder = {
		"autor",
		"titol",
		"arxiu"
})
public class Canso {
	@XmlElement(required = true)
	protected String autor;
	@XmlElement(required = true)
	protected String titol;
	@XmlElement(required = true)
	protected String arxiu;

	public Canso() {
		this.autor = "";
		this.titol = "";
		this.arxiu = "";
	}
	/**
	 * Constructor de la classe
	 * @param autor
	 * @param titol
	 * @param arxiu
	 */
	public Canso(String autor, String titol, String arxiu) {
		this.titol = titol;
		this.autor = autor;
		this.arxiu = arxiu;
	}

	// Getters i setters
	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getArxiu() {
		return arxiu;
	}

	public void setArxiu(String arxiu) {
		this.arxiu = arxiu;
	}

	@Override
	public String toString() {
		return autor + " - " + titol;
	}
}
