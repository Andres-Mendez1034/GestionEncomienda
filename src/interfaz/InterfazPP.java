package interfaz;

import java.util.Scanner;
import dao.Conexion;
import java.sql.SQLException;

public class InterfazPP {

    private EncomiendaInterfaz encomiendaInterfaz;
    private RemitenteInterfaz remitenteInterfaz;
    private EmpleadoInterfaz empleadoInterfaz;
    private NotificacionInterfaz notificacionInterfaz;
    private Scanner scanner;

    public InterfazPP() {
        this.encomiendaInterfaz = new EncomiendaInterfaz();
        this.remitenteInterfaz = new RemitenteInterfaz();
        this.empleadoInterfaz = new EmpleadoInterfaz();
        this.notificacionInterfaz = new NotificacionInterfaz(); // nuevo menú
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;

        while (opcion != 5) { // ahora hay 5 opciones
            System.out.println("\n*** Menú Principal ***");
            System.out.println("1. Gestión de Encomiendas");
            System.out.println("2. Gestión de Remitentes");
            System.out.println("3. Gestión de Empleados");
            System.out.println("4. Gestión de Notificaciones");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    encomiendaInterfaz.mostrarMenu();
                    break;
                case 2:
                    remitenteInterfaz.mostrarMenu();
                    break;
                case 3:
                    empleadoInterfaz.mostrarMenu();
                    break;
                case 4:
                    notificacionInterfaz.mostrarMenu(); // acceso al menú de notificaciones
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("[LOG] Probando conexión...");
        if (Conexion.getConnection() != null) {
            System.out.println("[LOG] Conexión establecida correctamente.");
        } else {
            System.out.println("[LOG] ERROR: No se pudo conectar a la base de datos.");
        }

        InterfazPP interfazPP = new InterfazPP();
        interfazPP.iniciar();
    }
}
