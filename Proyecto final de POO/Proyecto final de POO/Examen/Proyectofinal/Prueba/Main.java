package Proyectofinal.Prueba;

import Proyectofinal.vehicules.Camion;
import Proyectofinal.vehicules.Auto;
import Proyectofinal.vehicules.Camioneta;
import Proyectofinal.vehicules.Moto;
import Proyectofinal.Garajesss.Garaje;
import Proyectofinal.Garajesss.GestionEmpresa;
import Proyectofinal.Garajesss.Vehiculo;
import Proyectofinal.excepciones.EspacioInsuficienteException;
import Proyectofinal.excepciones.ExcesoCamionesException;
import Proyectofinal.excepciones.ExcesoMotosException;
import Proyectofinal.excepciones.GarajeException;
import Proyectofinal.excepciones.VehiculoExistenteException;

public class Main {
    public static void main(String[] args) {
        try {
         
            GestionEmpresa gestion = new GestionEmpresa();

    
            System.out.println("Creando garaje 1...");
            gestion.crearGaraje("Cundinamarca", "Bogotá", "Calle 100 #12-34", "123456789", "admin@garaje1.com", "Carlos Pérez", 120);

            System.out.println("Creando garaje 2...");
            gestion.crearGaraje("Antioquia", "Medellín", "Carrera 50 #1-25", "987654321", "admin@garaje2.com", "Ana Gómez", 80);

          
            Auto auto = new Auto("ABC123", 5000, 0, null, 0, false, false);   
            Moto moto = new Moto("MOTO123", 3000, 0, null, 0, false);   
            Camion camion = new Camion("CAM123", 15000, 5, null, 0, 0, null, 0); 

            
            Camioneta camionetaPickup = new Camioneta("PICKUP01", 20000, "Pickup", 2, true);

          
            Camioneta camionetaSUV = new Camioneta("SUV01", 25000, "SUV", 5, false);

         
            System.out.println("Ingresando vehículos al garaje 1");
            Garaje garaje1 = gestion.getRed().buscarGaraje(new Garaje("Cundinamarca", "Bogotá", "Calle 100 #12-34", "123456789", "admin@garaje1.com", "Carlos Pérez", 120));
            gestion.ingresarVehiculo(garaje1, auto);
            gestion.ingresarVehiculo(garaje1, moto);
            gestion.ingresarVehiculo(garaje1, camion);
            gestion.ingresarVehiculo(garaje1, camionetaPickup);

         
            System.out.println("vehículos al garaje 2...");
            Garaje garaje2 = gestion.getRed().buscarGaraje(new Garaje("Antioquia", "Medellín", "Carrera 50 #1-25", "987654321", "admin@garaje2.com", "Ana Gómez", 80));
            gestion.ingresarVehiculo(garaje2, camionetaSUV);

            
            System.out.println("informe de ocupación y recaudo");
            gestion.generarInforme();

            System.out.println("Intentando ingresar un vehículo duplicado");

            try {
                gestion.ingresarVehiculo(garaje2, moto); 
            } catch (VehiculoExistenteException e) {
                System.out.println("Excepcion: " + e.getMessage());
            }

            
            System.out.println("llenando el garaje 1");
            for (int i = 0; i < 200; i++) {  
                try {
                    Moto motoExtra = new Moto("MOTO" + (i + 124), 3000, i, null, i, false);
                    gestion.ingresarVehiculo(garaje1, motoExtra);
                } catch (GarajeException e) {
                    System.out.println("Excepción al agregar vehículo: " + e.getMessage());
                    break;
                }
            }

           
            System.out.println("retirar vehículo del garaje 1");
            gestion.retirarVehiculo(garaje1, moto);  

            
            System.out.println("informe de ocupación después de retirar un vehículo");
            gestion.generarInforme();

        } catch (GarajeException e) {
            e.printStackTrace();
        }
    }
}
