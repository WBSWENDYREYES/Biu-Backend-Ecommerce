package com.wendyecommerce.biuwendyecommerce.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
// 🔑 CORREGIDO: Usamos tu columna física 'idRoles' como discriminador y le decimos que es de tipo ENTERO (INTEGER)
@DiscriminatorColumn(name = "idRoles", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    
    private String nombre;
    private String telefonos;
    private String email;
    private String password;
    private int iddireccion;

    // 🔗 CORREGIDO: Apuntamos al objeto Roles para limpiar los errores del controlador,
    // pero mapeamos la columna física real 'idRoles' de tu base de datos.
    @ManyToOne
    @JoinColumn(name = "idRoles", insertable = false, updatable = false) 
    private Roles rol;

    // Constructor Vacío obligatorio para JPA
    public Usuario() {
    }

    // Constructor con parámetros corregido usando la entidad Roles
    public Usuario(int idusuario, String nombre, int iddireccion, String telefonos, String email, String password, Roles rol) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.iddireccion = iddireccion;
        this.telefonos = telefonos;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public int getIdusuario() { return idusuario; }
    public void setIdusuario(int idusuario) { this.idusuario = idusuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefonos() { return telefonos; }
    public void setTelefonos(String telefonos) { this.telefonos = telefonos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getIddireccion() { return iddireccion; }
    public void setIddireccion(int iddireccion) { this.iddireccion = iddireccion; }

    // Getters y Setters para el objeto Roles (Mantiene en limpio tu UsuarioController)
    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }
}