package Proyectofinal.vehicules;

import Proyectofinal.Garajesss.Vehiculo;

public class Auto extends Vehiculo {
  
  private boolean tieneRadio;
  private boolean tieneNavegador;



  public Auto(String marca, double precio, int cilindrada, String matricula, double cuaotaMesGarage, boolean tieneRadio,
      boolean tieneNavegador) {
    super(marca, precio, cilindrada, matricula, cuaotaMesGarage);
    this.tieneRadio = tieneRadio;
    this.tieneNavegador = tieneNavegador;
  }

public void calcularImpuestoCirculacion(){
    if (this.tieneRadio) {
      this.setImpuestoCirculacion( this.precio*(0.01+1));
    }
    if (this.tieneNavegador) {
      this.setImpuestoCirculacion( this.precio*(0.02+1));
    }
      
  }
  
  public void calcularcuotaMesGarage(){
    if (this.cilindrada>2499) {
      this.setCuotaMesGarage(this.precio*(0.2+1));
    }
  }
 }
