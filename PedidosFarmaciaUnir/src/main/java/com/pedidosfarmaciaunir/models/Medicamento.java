/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedidosfarmaciaunir.models;

import java.io.Serializable;

/**
 *
 * @author marco
 */
public class Medicamento implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // Atributos principales
    private String nombreMedicamento;
    private String tipoMedicamento;
    private int cantidad;
    private String distribuidor;
    private String sucursal;
    
    
    public Medicamento() {
        
    }
    
    public Medicamento(String nombreMedicamento, String tipoMedicamento, 
                      int cantidad, String distribuidor, String sucursal) {
        this.nombreMedicamento = nombreMedicamento;
        this.tipoMedicamento = tipoMedicamento;
        this.cantidad = cantidad;
        this.distribuidor = distribuidor;
        this.sucursal = sucursal;
    }
    
    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
    
    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }
    
    public String getTipoMedicamento() {
        return tipoMedicamento;
    }
    
    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getDistribuidor() {
        return distribuidor;
    }
    
    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }
    
    public String getSucursal() {
        return sucursal;
    }
    
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    // Validaciones
    public boolean isValid() {
        return nombreMedicamento != null && !nombreMedicamento.trim().isEmpty() &&
               tipoMedicamento != null && !tipoMedicamento.trim().isEmpty() &&
               cantidad >= 0 &&
               distribuidor != null && !distribuidor.trim().isEmpty() &&
               sucursal != null && !sucursal.trim().isEmpty();
    }
    
    // Enums para valores predefinidos
    public enum TipoDistribuidor {
        COFARMA("Cofarma"),
        EMPSEPHAR("Empsephar"),
        CEMEFAR("Cemefar");
        
        private final String nombre;
        
        TipoDistribuidor(String nombre) {
            this.nombre = nombre;
        }
        
        public String getNombre() {
            return nombre;
        }
    }
    
    public enum TipoSucursal {
        PRINCIPAL("Principal"),
        SECUNDARIA("Secundaria");
        
        private final String nombre;
        
        TipoSucursal(String nombre) {
            this.nombre = nombre;
        }
        
        public String getNombre() {
            return nombre;
        }
    }
    
    public enum TipoMedicamento {
        ANALGESICO("Analgésico"),
        ANALEPTICO("Analéptico"),
        ANESTESICO("Anestésico"),
        ANTIACIDO("Antiácido"),
        ANTIDEPRESIVO("Antidepresivo"),
        ANTIBIOTICO("Antibiótico");
        
        private final String nombre;
        
        TipoMedicamento(String nombre) {
            this.nombre = nombre;
        }
        
        public String getNombre() {
            return nombre;
        }
    }
}
