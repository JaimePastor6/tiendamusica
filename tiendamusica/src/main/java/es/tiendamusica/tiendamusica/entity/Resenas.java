package es.tiendamusica.tiendamusica.entity;

import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Resenas")
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "Calificacion", nullable = false)
    private int calificacion;

    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "FechaResena", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaResena;

    @ManyToOne
    @JoinColumn(name = "IDProducto", referencedColumnName = "ID")
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "IDCliente", referencedColumnName = "ID")
    private Clientes cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(Date fechaResena) {
        this.fechaResena = fechaResena;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

   
}
