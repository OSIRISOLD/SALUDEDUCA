package saludeduca;

import jade.core.Agent;

import java.util.ArrayList;
import java.util.Scanner;

public class SALUDEDUCA extends Agent {
    private Scanner scanner;
    private GestorEjercicios gestorEjercicios;
    private CriteriosUsuarios criteriosUsuarios;

    @Override
    protected void setup() {
        scanner = new Scanner(System.in);
        gestorEjercicios = new GestorEjercicios("C:\\Users\\LuisS\\OneDrive\\Documentos\\NetBeansProjects\\SALUDEDUCA\\src\\saludeduca\\Rutinas.txt");
        criteriosUsuarios = new CriteriosUsuarios(scanner);
        System.out.println("Agente " + getLocalName() + " iniciado.");

        boolean continuar = true;
        while (continuar) {
            Usuario usuario = solicitarDatosUsuario();
            Ejercicio ejercicioRecomendado = gestorEjercicios.obtenerEjercicioPorRangoEdad(usuario.getObjetivoFitness(), usuario.getEdad());
            if (ejercicioRecomendado != null) {
                System.out.println("El agente te recomienda este ejercicio: " + ejercicioRecomendado.getNombre());
                String ejercicio = ejercicioRecomendado.getNombre();
                String recomendacion = solicitarRecomendacion(ejercicio);
                criteriosUsuarios.guardarRecomendacion(ejercicio, recomendacion);
            } else {
                System.out.println("No se encontró ningún ejercicio recomendado.");
            }

            mostrarOpciones(usuario);
            continuar = preguntarSiContinuar();
        }

        scanner.close();
        System.out.println("Finalizando agente " + getLocalName() + ".");
        doDelete();
    }

    private Usuario solicitarDatosUsuario() {
        System.out.print("Por favor, ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        String objetivoFitness = presentarOpcionesFitness();
        int edad = solicitarEdad();
        return new Usuario(nombre, objetivoFitness, edad);
    }

    private String presentarOpcionesFitness() {
        System.out.println("Elija el objetivo de fitness:");
        System.out.println("1. Fuerza");
        System.out.println("2. Resistencia");
        System.out.println("3. Ganar Músculo");
        System.out.println("4. Perder Peso");
        int opcion = obtenerOpcionFitness();
        return convertirOpcionAObjetivo(opcion);
    }

    private int obtenerOpcionFitness() {
        int opcion;
        do {
            System.out.print("Ingrese una opción (1-4): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    private int solicitarEdad() {
        System.out.print("Por favor, ingrese la edad del usuario: ");
        int edad;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese una edad válida.");
                scanner.next();
            }
            edad = scanner.nextInt();
            scanner.nextLine();
        } while (edad <= 0);
        return edad;
    }

    private String solicitarRecomendacion(String ejercicio) {
        System.out.println("Por favor, ingrese su recomendación para el ejercicio '" + ejercicio + "':");
        String recomendacion = scanner.nextLine();
        System.out.println("Recomendación guardada correctamente.");
        return recomendacion;
    }

    private void mostrarOpciones(Usuario usuario) {
        System.out.println("Deseas ver todas las rutinas del objetivo fitness seleccionado? (S/N)");
        String respuesta = scanner.nextLine().trim();
        if (respuesta.equalsIgnoreCase("S")) {
            mostrarTodasLasRutinas(usuario.getObjetivoFitness());
            criteriosUsuarios.cargarRecomendaciones();
        }
    }

    private void mostrarTodasLasRutinas(String objetivoFitness) {
        ArrayList<Ejercicio> ejercicios = gestorEjercicios.obtenerEjercicios(objetivoFitness);
        if (ejercicios != null && !ejercicios.isEmpty()) {
            System.out.println("Todas las rutinas para el objetivo de fitness " + objetivoFitness + ":");
            for (Ejercicio ejercicio : ejercicios) {
                // Imprimiendo todos los detalles del ejercicio
                System.out.println("Nombre: " + ejercicio.getNombre() +
                        ", Categoría: " + ejercicio.getCategoria() +
                        ", Intensidad: " + ejercicio.getIntensidad() +
                        ", Duración (minutos): " + ejercicio.getDuracion() +
                        ", Calificación: " + ejercicio.getRating() +
                        ", Edad mínima: " + ejercicio.getEdadMinima() +
                        ", Edad máxima: " + ejercicio.getEdadMaxima());
            }
        } else {
            System.out.println("No hay rutinas disponibles para el objetivo de fitness seleccionado.");
        }
    }

    private boolean preguntarSiContinuar() {
        System.out.println("¿Desea introducir otro usuario? (S/N)");
        String respuesta = scanner.nextLine().trim();
        return respuesta.equalsIgnoreCase("S");
    }

    private String convertirOpcionAObjetivo(int opcion) {
        switch (opcion) {
            case 1:
                return "Fuerza";
            case 2:
                return "Resistencia";
            case 3:
                return "Ganar Músculo";
            case 4:
                return "Perder Peso";
            default:
                return "Fuerza"; // No debería llegar aquí
        }
    }
}
