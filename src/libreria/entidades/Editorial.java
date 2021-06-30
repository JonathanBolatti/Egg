package libreria.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Editorial implements Serializable {
	
	@Id
	private String id; 
	@Basic
	private String nombre; 

	public Editorial() {
	}

	public Editorial(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Editorial{" + "id=" + id + ", nombre=" + nombre + '}';
	}

	
	
}
