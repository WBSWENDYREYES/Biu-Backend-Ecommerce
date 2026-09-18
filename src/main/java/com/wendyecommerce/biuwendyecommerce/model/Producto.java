package com.wendyecommerce.biuwendyecommerce.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Producto")
public class Producto {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencia;
@ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private Categoria categoria;
@ManyToOne
    @JoinColumn(name = "idMarca", referencedColumnName = "id")
    private Marca marca;
    // Constructor
  public Producto() {}
    // Métodos (Propiedades)
    public int getidproducto () {
        return id;
    }
    public void setidproducto (int idproducto) {
        this.id = idproducto;
    }
    public String getReferencia() {
        return referencia;
    }    
    public void setReferencia(String referencia) {
    	this.referencia = referencia;
    }  
    public String getNombre() {
        return nombre;
    }
    public void setNombre( String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getExistencia() {
        return existencia;
    }
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
    
    public String getDetalles() {
        return "Producto: " + descripcion + " - Precio: " + precio;
    }

    
}
