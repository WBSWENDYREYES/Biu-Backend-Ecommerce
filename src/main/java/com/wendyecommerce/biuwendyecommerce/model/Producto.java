package com.wendyecommerce.biuwendyecommerce.model;

public class Producto {
	private int id;
    private String referencia;
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencia;
    private int idCategoria;
    private int idMarca;
    private String imagen;
    private String nombreCategoria;
    private String nombreMarca;
  // Constructor
  public Producto() {}
    // Métodos (Propiedades)
    public int getid () {
        return id;
    }
    public void setid (int id) {
        this.id = id;
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

 public int getidCategoria() {
        return idCategoria;
    }
    
    public void setidCategoria(int idcategoria) {
        this.idCategoria = idcategoria;
    
}

 public int getidMarca() {
        return idMarca;
    }
    
    public void setidMarca(int idmarca) {
        this.idMarca = idmarca;
    
}
 public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

 public String getnombreCategoria() {
        return nombreCategoria;
    }
    public void setnombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }    

 public String getnombreMarca() {
        return nombreMarca;
    }
    public void setnombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }    

 @Override
public String toString() {
    return "Producto{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", referencia='" + referencia + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", existencia=" + existencia +
            ", precio=" + precio +
            ", idCategoria=" + idCategoria +
            ", idMarca=" + idMarca +
            ", imagen='" + imagen + '\'' +
            '}';
}   

}