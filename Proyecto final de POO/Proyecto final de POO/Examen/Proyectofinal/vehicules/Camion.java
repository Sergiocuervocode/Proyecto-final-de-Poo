
package Proyectofinal.vehicules;

import Proyectofinal.Garajesss.Vehiculo;

public class Camion extends Vehiculo {
  private int numeroDeEjes;
  private String tipoDeCamion; 
  private double capacidadDeCarga;
  

  public Camion(String marca, double precio, int cilindrada, String matricula, double cuotaMesGarage,
                int numeroDeEjes, String tipoDeCamion, double capacidadDeCarga) {
    super(marca, precio, cilindrada, matricula, cuotaMesGarage);
    this.numeroDeEjes = numeroDeEjes;
    this.tipoDeCamion = tipoDeCamion;
    this.capacidadDeCarga = capacidadDeCarga;
    
    calcularImpuestoCirculacion();
    calcularCuotaMesGarage();
  }


  public int getNumeroDeEjes() {
    return numeroDeEjes;
  }

  public void setNumeroDeEjes(int numeroDeEjes) {
    this.numeroDeEjes = numeroDeEjes;
  }

  public String getTipoDeCamion() {
    return tipoDeCamion;
  }

  public void setTipoDeCamion(String tipoDeCamion) {
    this.tipoDeCamion = tipoDeCamion;
  }

  public double getCapacidadDeCarga() {
    return capacidadDeCarga;
  }

  public void setCapacidadDeCarga(double capacidadDeCarga) {
    this.capacidadDeCarga = capacidadDeCarga;
  }


  @Override
  public void calcularImpuestoCirculacion() {
    double impuestoBase = this.precio * 0.09;
    double impuestoPorCarga = Math.floor(this.capacidadDeCarga / 5) * 10;  
    this.impuestoCirculacion = impuestoBase + impuestoPorCarga;
  }

  public void calcularCuotaMesGarage() {
    if (this.tipoDeCamion.equals("Sencillo") && this.numeroDeEjes == 2) {
      this.setCuotaMesGarage(this.precio * (0.75 + 1)); 
    } else if (this.tipoDeCamion.equals("Doble") && this.numeroDeEjes >= 3 && this.numeroDeEjes <= 6) {
      this.setCuotaMesGarage(this.precio * (1.25 + 1)); 
    }
  }
}