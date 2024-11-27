package Proyectofinal.vehicules;

import Proyectofinal.Garajesss.Vehiculo;

public class Camioneta extends Vehiculo {
    private String tipoServicio; 
    private int numeroDePasajeros;
    private boolean tieneRemolque;


    public Camioneta(String matricula, double precio, String tipoServicio, int numeroDePasajeros, boolean tieneRemolque) {
        super(matricula, precio, numeroDePasajeros, tipoServicio, numeroDePasajeros); 
        

        if (tipoServicio.equals("Pickup") || tipoServicio.equals("Carga")) {
            if (numeroDePasajeros > 2) {
                throw new IllegalArgumentException("Las camionetas Pickup y Carga solo pueden tener 2 pasajeros.");
            }
        } else {
            if (numeroDePasajeros > 5) {
                throw new IllegalArgumentException("El número de pasajeros no puede ser mayor a 5.");
            }
        }

        this.tipoServicio = tipoServicio;
        this.numeroDePasajeros = numeroDePasajeros;
        this.tieneRemolque = tieneRemolque;
    }


    public String getTipoServicio() {
        return tipoServicio;
    }

    public int getNumeroDePasajeros() {
        return numeroDePasajeros;
    }

    public boolean tieneRemolque() {
        return tieneRemolque;
    }

    
  
    public double calcularImpuesto() {
        return this.getPrecio() * 0.05;
    }


    @Override
    public double getCuotaMesGarage() {
        double cuotaBase = super.getCuotaMesGarage();  
        

        if (tipoServicio.equals("Pickup") || tipoServicio.equals("Carga") || tipoServicio.equals("Otro")) {
            cuotaBase *= 1.45;  
        } else if (tipoServicio.equals("SUV")) {
            cuotaBase *= 1.10;
        }
        

        if (numeroDePasajeros == 2) {
            cuotaBase *= 1.50; 
        } else if (numeroDePasajeros > 2) {
            cuotaBase *= 1.60;  
        }

    
        if (tieneRemolque) {
            cuotaBase *= 1.10;
        }
        
        return cuotaBase;
    }
    

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Servicio: " + tipoServicio + 
               ", Pasajeros: " + numeroDePasajeros + ", Tiene Remolque: " + tieneRemolque;
    }
}