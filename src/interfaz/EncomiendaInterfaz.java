package interfaz;

import controller.EncomiendaController;
import model.Encomienda;
import model.Tarifa;
import java.util.Scanner;

public class EncomiendaInterfaz {

    private EncomiendaController encomiendaController;
    private Scanner scanner;

    public EncomiendaInterfaz() {
        this.encomiendaController = new EncomiendaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n*** Menú Encomiendas ***");
            System.out.println("1. Registrar Encomienda");
            System.out.println("2. Despachar Encomienda");
            System.out.println("3. Ver Encomienda");
            System.out.println("4. Calcular Valor de la Encomienda");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Captura el salto de línea

            switch (opcion) {
                case 1: registrarEncomienda(); break;
                case 2: despacharEncomienda(); break;
                case 3: verEncomienda(); break;
                case 4: calcularValorEncomienda(); break;
                case 5: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida, intenta nuevamente.");
            }
        }
    }

    private void registrarEncomienda() {
        System.out.print("Número de Guía (o dejar vacío para generar): ");
        String numeroGuia = scanner.nextLine();
        if (numeroGuia.trim().isEmpty()) {
            numeroGuia = model.GuiaGenerator.generarNumero();
            System.out.println("Guía generada: " + numeroGuia);
        }

        System.out.print("Peso: ");
        float peso = scanner.nextFloat();
        scanner.nextLine(); // Captura el salto de línea

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        encomiendaController.registrarEncomienda(numeroGuia, peso, destino);
    }

    private void despacharEncomienda() {
        System.out.print("Número de Guía de la encomienda a despachar: ");
        String numeroGuia = scanner.nextLine();
        encomiendaController.despacharEncomienda(numeroGuia);
    }

    private void verEncomienda() {
        System.out.print("Número de Guía de la encomienda a ver: ");
        String numeroGuia = scanner.nextLine();

        Encomienda encomienda = encomiendaController.obtenerEncomienda(numeroGuia);
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

    private void calcularValorEncomienda() {
        System.out.print("Número de Guía de la encomienda para calcular su valor: ");
        String numeroGuia = scanner.nextLine();

        Encomienda encomienda = encomiendaController.obtenerEncomienda(numeroGuia);
        if (encomienda != null) {
            // Crear una instancia de Tarifa
            Tarifa tarifa = new Tarifa();

            float valor = tarifa.calcularCosto(encomienda);

            System.out.println("El valor de la encomienda con número de guía " + numeroGuia + " es: " + valor);
        } else {
            System.out.println("Encomienda no encontrada.");
        }
    }
}
