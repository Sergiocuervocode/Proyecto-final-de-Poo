package Proyectofinal.Garajesss;

public class Vehiculo {
  public String matricula;
  public String marca;
  public double precio;
  public int cilindrada;
  public double impuestoCirculacion;
  public double cuotaMesGarage;
  public static final int CUOTA_MES_GARAJE = 100;

  public Vehiculo(String marca, double precio, int cilindrada, String matricula, double cuotaMesGarage) {
    this.marca = marca;
    this.precio = precio;
    this.cilindrada = cilindrada;
    this.cuotaMesGarage = CUOTA_MES_GARAJE;
    this.matricula = null;

  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public int getCilindrada() {
    return cilindrada;
  }

  public void setCilindrada(int cilindrada) {
    this.cilindrada = cilindrada;
  }

  public double getImpuestoCirculacio() {
    return impuestoCirculacion;
  }

  public void setImpuestoCirculacion(double impuestoCirculacio) {
    this.impuestoCirculacion = impuestoCirculacio;
  }

  public void setCuotaMesGarage(double cuotaMesGarage) {
    if (cuotaMesGarage>0) {
      this.cuotaMesGarage = cuotaMesGarage;
    } 
  }

  public double getCuotaMesGarage() {
    return cuotaMesGarage;
  }

  public void calcularImpuestoCirculacion() {
    this.impuestoCirculacion = this.precio * 0.02;
  }

  public boolean matricula(String matricula) {
    return matricula.length() == 6;
  }

}