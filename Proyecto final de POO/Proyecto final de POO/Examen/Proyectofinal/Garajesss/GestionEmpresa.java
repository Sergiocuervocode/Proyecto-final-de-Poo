package Proyectofinal.Garajesss;

import Proyectofinal.vehicules.Camion;
import Proyectofinal.vehicules.Auto;
import Proyectofinal.vehicules.Camioneta;
import Proyectofinal.vehicules.Moto;
import Proyectofinal.excepciones.EspacioInsuficienteException;
import Proyectofinal.excepciones.ExcesoCamionesException;
import Proyectofinal.excepciones.ExcesoMotosException;
import Proyectofinal.excepciones.GarajeException;
import Proyectofinal.excepciones.VehiculoExistenteException;

public class GestionEmpresa {
    private RedDeGarajes red;

    public GestionEmpresa() {
        this.red = new RedDeGarajes();
    }

    public void crearGaraje(String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int espacios) throws GarajeException {
        Garaje garaje = new Garaje(departamento, ciudad, direccion, telefono, email, nombreAdministrador, espacios);
        red.agregarGaraje(garaje);
    }

    public void eliminarGaraje(Garaje garaje) throws GarajeException {
        red.eliminarGaraje(garaje);
    }

    public void ingresarVehiculo(Garaje garaje, Vehiculo vehiculo) throws GarajeException {
        garaje.ingresarVehiculo(vehiculo);
    }

    public void retirarVehiculo(Garaje garaje, Vehiculo vehiculo) throws GarajeException {
        garaje.retirarVehiculo(vehiculo);
    }

    public void generarInforme() {
        red.generarInformeOcupacion();
        red.generarInformeRecaudo();
    }

    public RedDeGarajes getRed() {
        return this.red;
    }
}
