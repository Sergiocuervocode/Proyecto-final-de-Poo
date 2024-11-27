package Proyectofinal.Prueba;

import Proyectofinal.Garajesss.GestionEmpresa;
import Proyectofinal.Garajesss.Garaje;
import Proyectofinal.Garajesss.Vehiculo;
import Proyectofinal.vehicules.Auto;
import Proyectofinal.vehicules.Moto;
import Proyectofinal.vehicules.Camion;
import Proyectofinal.vehicules.Camioneta;
import Proyectofinal.excepciones.GarajeException;
import Proyectofinal.excepciones.VehiculoExistenteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static GestionEmpresa gestionEmpresa;
    private static Scanner scanner;
    private List<Vehiculo> vehiculos;


    public Menu() {
        gestionEmpresa = new GestionEmpresa();
        scanner = new Scanner(System.in);
        this.vehiculos = new ArrayList<>();
    }

    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println(" MENÚ DE GESTIÓN DE GARAJES");
            System.out.println("1. Crear garaje");
            System.out.println("2. Ingresar vehículo a un garaje");
            System.out.println("3. Retirar vehículo de un garaje");
            System.out.println("4. Generar informes de ocupación y recaudo");
            System.out.println("5. Contar vehiculos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearGaraje();
                    break;
                case 2:
                    ingresarVehiculo();
                    break;
                case 3:
                    retirarVehiculo();
                    break;
                case 4:
                    generarInformes();
                    break;
                case 5:
                    InformeContarVehiculos();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void crearGaraje() {
        System.out.println("CREAR GARAGE");
        scanner.nextLine();  
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nombre del administrador: ");
        String nombreAdministrador = scanner.nextLine();
        System.out.print("Número de espacios: ");
        int espacios = scanner.nextInt();

        try {
            gestionEmpresa.crearGaraje(departamento, ciudad, direccion, telefono, email, nombreAdministrador, espacios);
            System.out.println("Garaje creado con exito.");
        } catch (GarajeException e) {
            System.out.println("Error al crear el garaje: " + e.getMessage());
        }
    }

    private void ingresarVehiculo() {
        System.out.println("INGRESAR VEHÍCULO");
        scanner.nextLine(); 
        System.out.print("Matricula del vehículo: ");
        String matricula = scanner.nextLine();
        System.out.print("Marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.print("Precio del vehículo: ");
        double precio = scanner.nextDouble();
        System.out.print("Cilindrada del vehículo: ");
        int cilindrada = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo de vehículo:");
        System.out.println("1. Auto");
        System.out.println("2. Moto");
        System.out.println("3. Camión");
        System.out.println("4. Camioneta");
        System.out.print("Seleccione el tipo: ");
        int tipoVehiculo = scanner.nextInt();

        Vehiculo vehiculo = null;

        switch (tipoVehiculo) {
            case 1:
                vehiculo = new Auto(matricula, precio, cilindrada, null, 0, false, false);
                break;
            case 2:
                vehiculo = new Moto(matricula, precio, cilindrada, null, 0, false);
                break;
            case 3:
                vehiculo = new Camion(matricula, precio, cilindrada, null, 0, 0, null, 0);
                break;
            case 4:
                scanner.nextLine(); 
                System.out.print("Tipo de camioneta (pickup/SUV): ");
                String tipoCamioneta = scanner.nextLine();
                vehiculo = new Camioneta(matricula, precio, tipoCamioneta, 2, false);
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return;
        }

        scanner.nextLine();  
        System.out.print("Dirección del garaje (se requiere para ingresar el vehículo): ");
        String direccionGaraje = scanner.nextLine();

        Garaje garaje = gestionEmpresa.getRed().buscarGaraje(new Garaje("", "", direccionGaraje, "", "", "", 0));
        if (garaje == null) {
            System.out.println("Garaje no encontrado.");
            return;
        }

        try {
            gestionEmpresa.ingresarVehiculo(garaje, vehiculo);
            System.out.println("Vehículo ingresado correctamente.");
        } catch (GarajeException e) {
            System.out.println("Error al ingresar el vehículo: " + e.getMessage());
        }
    }

    private void retirarVehiculo() {
        System.out.println("RETIRAR VEHÍCULO");
        scanner.nextLine(); 
        System.out.print("Matricula del vehículo a retirar: ");
        String matricula = scanner.nextLine();

        System.out.print("Dirección del garaje donde se encuentra el vehículo: ");
        String direccionGaraje = scanner.nextLine();

        Garaje garaje = gestionEmpresa.getRed().buscarGaraje(new Garaje("", "", direccionGaraje, "", "", "", 0));
        if (garaje == null) {
            System.out.println("Garaje no encontrado.");
            return;
        }

        Vehiculo vehiculo = new Vehiculo("", 0, 0, matricula, 0);
        try {
            gestionEmpresa.retirarVehiculo(garaje, vehiculo);
            System.out.println("Vehículo retirado correctamente.");
        } catch (GarajeException e) {
            System.out.println("Error al retirar el vehículo: " + e.getMessage());
        }
    }

    private void generarInformes() {
        System.out.println("GENERAR INFORMES");
        gestionEmpresa.generarInforme();
    }

    public String InformeContarVehiculos() {
      int autos = contarAutos();
      int motos = contarMotos();
      int camiones = contarCamiones();
      int camionetas = contarCamionetas();
  

      int camionesPequenos = contarCamionesPorTipo("Sencillo");
      int camionesGrandes = contarCamionesPorTipo("Doble");
  
      
      int camionetasSUV = contarCamionetasPorTipo("SUV");
      int camionetasPickup = contarCamionetasPorTipo("Pickup");
      int camionetasCarga = contarCamionetasPorTipo("Carga");
      int camionetasOtro = contarCamionetasPorTipo("Otro");
  
 
      String informe = String.format("Informe de ocupación del garaje:" + "Autos: %d" +"Motos: %d" +"Camiones: %d (Camiones pequeños: %d, Camiones grandes: %d)" +"Camionetas: %d (SUV: %d, Pickup: %d, Carga: %d, Otro: %d)", autos, motos, camiones, camionesPequenos, camionesGrandes, camionetas, camionetasSUV, camionetasPickup, camionetasCarga, camionetasOtro);
  
    return informe;
  }
  
  private int contarAutos() {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos) {
          if (vehiculo instanceof Auto) {
              contador++;
          }
      }
      return contador;
  }
  
  private int contarMotos() {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos) {
          if (vehiculo instanceof Moto) {
              contador++;
          }
      }
      return contador;
  }
  
  private int contarCamiones() {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos) {
          if (vehiculo instanceof Camion) {
              contador++;
          }
      }
      return contador;
  }
  
  private int contarCamionetas() {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos) {
          if (vehiculo instanceof Camioneta) {
              contador++;
          }
      }
      return contador;
  }
  

  private int contarCamionesPorTipo(String tipo) {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos) {
          if (vehiculo instanceof Camion) {
              Camion camion = (Camion) vehiculo;
              if (camion.getTipoDeCamion().equals(tipo)) {
                  contador++;
              }
          }
      }
      return contador;
  }
  

  private int contarCamionetasPorTipo(String tipo) {
      int contador = 0;
      for (Vehiculo vehiculo : vehiculos){
          if (vehiculo instanceof Camioneta) {
              Camioneta camioneta = (Camioneta) vehiculo;
              if (camioneta.getTipoServicio().equals(tipo)) {
                  contador++;
              }
          }
      }
      return contador;
  }
  

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}
