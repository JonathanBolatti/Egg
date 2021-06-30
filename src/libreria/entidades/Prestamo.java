package libreria.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prestamo implements Serializable {
	@Id
	private String id;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.DATE)
	private Date devolucion;

	public Prestamo() {
	}

	public Prestamo(String id, Date fecha, Date devolucion) {
		this.id = id;
		this.fecha = fecha;
		this.devolucion = devolucion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Date devolucion) {
		this.devolucion = devolucion;
	}
	
	

}
