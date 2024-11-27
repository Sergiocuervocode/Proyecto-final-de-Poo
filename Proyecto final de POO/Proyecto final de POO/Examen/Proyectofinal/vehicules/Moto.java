package Proyectofinal.vehicules;

import Proyectofinal.Garajesss.Vehiculo;

public class Moto extends Vehiculo {
  private boolean tieneSidecar;

  public Moto(String marca, double precio, int cilindrada, String matricula, double cuaotaMesGarage,
      boolean tieneSidecar) {
    super(marca, precio, cilindrada, matricula, cuaotaMesGarage);
    this.tieneSidecar = tieneSidecar;
  }

  public void calcularImpuestoCirculacion() {
    if (this.tieneSidecar) {
      this. setImpuestoCirculacion( + this.precio* (0.1+1));
    }
  }

  public void calcularcuotaMesGarage() {
    if (this.tieneSidecar) {
      this.setCuotaMesGarage( this.precio * (0.5+1) );
    }
  }
}
