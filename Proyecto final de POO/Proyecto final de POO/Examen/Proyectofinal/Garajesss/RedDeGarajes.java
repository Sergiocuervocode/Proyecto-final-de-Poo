package Proyectofinal.Garajesss;

import Proyectofinal.excepciones.EspacioInsuficienteException;
import Proyectofinal.excepciones.ExcesoCamionesException;
import Proyectofinal.excepciones.ExcesoMotosException;
import Proyectofinal.excepciones.GarajeException;
import Proyectofinal.excepciones.VehiculoExistenteException;

import java.util.ArrayList;

public class RedDeGarajes {
    private ArrayList<Garaje> garajes;

    public RedDeGarajes() {
        this.garajes = new ArrayList<>();
    }

    public void agregarGaraje(Garaje garaje) throws GarajeException {
        if (buscarGaraje(garaje) != null) {
            throw new GarajeException("El garaje ya existe.");
        }
        garajes.add(garaje);
    }

    public void eliminarGaraje(Garaje garaje) throws GarajeException {
        Garaje existente = buscarGaraje(garaje);
        if (existente == null) {
            throw new GarajeException("Garaje no encontrado.");
        }
        garajes.remove(existente);
    }

    public Garaje buscarGaraje(Garaje garaje) {
        for (Garaje g : garajes) {
            if (g.equals(garaje)) {
                return g;
            }
        }
        return null;
    }


    public void generarInformeOcupacion() {
        for (Garaje g : garajes) {
            System.out.println("Garaje en " + g.getDireccion() + ": " + g.obtenerOcupacion() + "/" + g.getEspacios() + " espacios ocupados.");
        }
    }

    public void generarInformeRecaudo() {
        double recaudoTotal = 0;
    
        for (Garaje g : garajes) {
            double recaudoGaraje = g.calcularIngresos();
            recaudoTotal += recaudoGaraje;
    
   
            double mensual = recaudoGaraje / 12;
    
           
            System.out.println("Recaudo de garaje " + g.getDireccion() + ": " + recaudoGaraje);
            System.out.println("Recaudo mensual de garaje " + g.getDireccion() + ": " + mensual);
        }
    
  
        System.out.println("Recaudo total de la red de garajes: " + recaudoTotal);
    }
    
}
