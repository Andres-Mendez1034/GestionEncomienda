package interfaz;

import controller.RemitenteController;
import model.Remitente;
import java.util.Scanner;

public class RemitenteInterfaz {

    private RemitenteController remitenteController;
    private Scanner scanner;

    public RemitenteInterfaz() {
        this.remitenteController = new RemitenteController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n*** Menú Remitente ***");
            System.out.println("1. Registrar Remitente");
            System.out.println("2. Actualizar Información");
            System.out.println("3. Ver Remitente");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: registrarRemitente(); break;
                case 2: actualizarRemitente(); break;
                case 3: verRemitente(); break;
                case 4: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }

    private void registrarRemitente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        remitenteController.registrarRemitente(nombre, direccion, telefono);
    }

    private void actualizarRemitente() {
        System.out.print("Teléfono del remitente a actualizar: ");
        String telefono = scanner.nextLine();

        System.out.print("Nuevo Nombre: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nueva Dirección: ");
        String nuevaDireccion = scanner.nextLine();

        System.out.print("Nuevo Teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        remitenteController.actualizarRemitente(telefono, nuevoNombre, nuevaDireccion, nuevoTelefono);
    }

    private void verRemitente() {
        System.out.print("Teléfono del remitente a ver: ");
        String telefono = scanner.nextLine();

        Remitente remitente = remitenteController.obtenerRemitente(telefono);
        if (remitente != null) {
            System.out.println("\n*** Detalles del Remitente ***");
            System.out.println("Nombre: " + remitente.getNombre());
            System.out.println("Dirección: " + remitente.getDireccion());
            System.out.println("Teléfono: " + remitente.getTelefono());
        } else {
            System.out.println("Remitente no encontrado.");
        }
    }
}
