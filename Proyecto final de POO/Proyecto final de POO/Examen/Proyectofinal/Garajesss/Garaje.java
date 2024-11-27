package Proyectofinal.Garajesss;

import java.util.ArrayList;
import Proyectofinal.vehicules.Camion;
import Proyectofinal.vehicules.Auto;
import Proyectofinal.vehicules.Camioneta;
import Proyectofinal.vehicules.Moto;
import Proyectofinal.excepciones.EspacioInsuficienteException;
import Proyectofinal.excepciones.ExcesoCamionesException;
import Proyectofinal.excepciones.ExcesoMotosException;
import Proyectofinal.excepciones.GarajeException;
import Proyectofinal.excepciones.VehiculoExistenteException;


public class Garaje {
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String nombreAdministrador;
    private int espacios;
    private ArrayList<Vehiculo> vehiculos;

    private static final int MAX_CAMIONES_PEQUEÑO = 10;
    private static final int MAX_CAMIONES_GRANDE = 20;
    private static final double PORCENTAJE_MOTOS = 0.20;  // 20%

    public Garaje(String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int espacios) {
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;
        this.espacios = espacios;
        this.vehiculos = new ArrayList<>();
    }

    public void ingresarVehiculo(Vehiculo vehiculo) throws GarajeException {
        if (buscarVehiculo(vehiculo.getMatricula()) != -1) {
            throw new VehiculoExistenteException("El vehículo ya se encuentra registrado en otro garaje.");
        }

        int maxCamiones = (espacios < 100) ? MAX_CAMIONES_PEQUEÑO : MAX_CAMIONES_GRANDE;
        int camiones = contarVehiculosPorTipo(Camion.class);
        if (vehiculo instanceof Camion && camiones >= maxCamiones) {
            throw new ExcesoCamionesException("El garaje ha alcanzado el límite de camiones permitidos.");
        }

        int maxMotos = (int) (espacios * PORCENTAJE_MOTOS);
        int motos = contarVehiculosPorTipo(Moto.class);
        if (vehiculo instanceof Moto && motos >= maxMotos) {
            throw new ExcesoMotosException("El garaje ha alcanzado el límite de motos permitidos.");
        }

        if (vehiculos.size() >= espacios) {
            throw new EspacioInsuficienteException("No hay suficientes espacios en el garaje.");
        }

        vehiculos.add(vehiculo);
    }

    public void retirarVehiculo(Vehiculo vehiculo) throws GarajeException {
        int index = buscarVehiculo(vehiculo.getMatricula());
        if (index == -1) {
            throw new VehiculoExistenteException("El vehículo no se encuentra en el garaje.");
        }
        vehiculos.remove(index);
    }

    private int buscarVehiculo(String matricula) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getMatricula().equals(matricula)) {
                return i;
            }
        }
        return -1;
    }

    private int contarVehiculosPorTipo(Class<?> tipo) {
        int contador = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getClass().equals(tipo)) {
                contador++;
            }
        }
        return contador;
    }

    public double calcularIngresos() {
        double ingresos = 0.0;
        for (Vehiculo vehiculo : vehiculos) {
            ingresos += vehiculo.getCuotaMesGarage();
        }
        return ingresos;
    }

    public String obtenerInformes() {
        int autos = contarVehiculosPorTipo(Auto.class);
        int motos = contarVehiculosPorTipo(Moto.class);
        int camiones = contarVehiculosPorTipo(Camion.class);
        return String.format("Autos: %d, Motos: %d, Camiones: %d", autos, motos, camiones);
    }

    public int obtenerOcupacion() {
        return vehiculos.size();
    }

    public int obtenerEspaciosDisponibles() {
        return espacios - vehiculos.size();
    }

    public String getDireccion() {
            return ciudad;
    }

    public String getEspacios() {
            return ciudad;
    }
}
