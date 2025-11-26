package interfaz;

import controller.EmpleadoController;
import model.Encomienda;
import model.Empleado;
import java.util.Scanner;

public class EmpleadoInterfaz {

    private EmpleadoController empleadoController;
    private Scanner scanner;

    public EmpleadoInterfaz() {
        this.empleadoController = new EmpleadoController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n*** Menú Empleado ***");
            System.out.println("1. Asignar Recogida");
            System.out.println("2. Despachar Encomienda");
            System.out.println("3. Ver Encomienda");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: asignarRecogida(); break;
                case 2: despacharEncomienda(); break;
                case 3: verEncomienda(); break;
                case 4: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }

    private void asignarRecogida() {
        System.out.print("ID del empleado: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de Guía de la encomienda a recoger: ");
        String numeroGuia = scanner.nextLine();

        Empleado empleado = new Empleado(idEmpleado, "Empleado " + idEmpleado, "Recogida");

        Encomienda encomienda = empleadoController.buscarEncomienda(numeroGuia);
        if (encomienda == null) {
            System.out.println("Encomienda no encontrada para asignar.");
        } else {
            empleadoController.asignarRecogida(empleado, encomienda);
        }
    }

    private void despacharEncomienda() {
        System.out.print("ID del empleado: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de Guía de la encomienda a despachar: ");
        String numeroGuia = scanner.nextLine();

        Empleado empleado = new Empleado(idEmpleado, "Empleado " + idEmpleado, "Despacho");

        Encomienda encomienda = empleadoController.buscarEncomienda(numeroGuia);
        if (encomienda == null) {
            System.out.println("Encomienda no encontrada. Creando ejemplar de prueba.");
            encomienda = new Encomienda(numeroGuia, 10, "Destino Ejemplo");
        }

        empleadoController.despacharEncomienda(empleado, encomienda);
    }

    private void verEncomienda() {
        System.out.print("Número de Guía de la encomienda a ver: ");
        String numeroGuia = scanner.nextLine();

        Encomienda encomienda = empleadoController.buscarEncomienda(numeroGuia);
        if (encomienda != null) {
            System.out.println("\n*** Detalles de la Encomienda ***");
            System.out.println("Número de Guía: " + encomienda.getNumeroGuia());
            System.out.println("Peso: " + encomienda.getPeso());
            System.out.println("Destino: " + encomienda.getDestino());
            System.out.println("Estado: " + encomienda.getEstado());
        } else {
            System.out.println("Encomienda no encontrada.");
        }
    }
}
