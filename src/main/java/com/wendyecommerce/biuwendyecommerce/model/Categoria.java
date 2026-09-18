package com.wendyecommerce.biuwendyecommerce.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nombre;

                
        //  Constructor
        public Categoria() {}
        
        public int getid() {
            return id;
        }

        public void setid(int id) {
            this.id = id;
        }

        public String getnombre() {
            return nombre;
        }

        public void setnombre(String nombre) {
            this.nombre = nombre;
        }
}
