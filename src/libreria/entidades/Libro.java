
package libreria.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Libro implements Serializable {
	
	@Id
	private long isbn;
	@Basic
	private String titulo; 
	private Integer anio; 
	private Integer ejemplares; 
	private Integer prestados; 

	public Libro() {
	}

	public Libro(long isbn, String titulo, Integer anio, Integer ejemplares, Integer prestados) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.anio = anio;
		this.ejemplares = ejemplares;
		this.prestados = prestados;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Integer ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Integer getPrestados() {
		return prestados;
	}

	public void setPrestados(Integer prestados) {
		this.prestados = prestados;
	}

	@Override
	public String toString() {
		return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", prestados=" + prestados + '}';
	}
	
	
	
	
}
