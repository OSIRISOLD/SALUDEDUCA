package saludeduca;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;

public class SALUDEDUCA extends Agent {
    private Scanner scanner;
    private GestorEjercicios gestorEjercicios;

    @Override
    protected void setup() {
        scanner = new Scanner(System.in);
        gestorEjercicios = new GestorEjercicios("C:\\Users\\LuisS\\OneDrive\\Documentos\\NetBeansProjects\\SALUDEDUCA\\src\\saludeduca\\Rutinas.txt");
        System.out.println("Agente " + getLocalName() + " iniciado.");

        boolean continuar = true;
        while (continuar) {
            Usuario usuario = solicitarDatosUsuario();
            enviarInformacionUsuario(usuario);
            mostrarEjercicioPorRangoEdad(usuario);
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

    private int solicitarEdad() {
        System.out.print("Por favor, ingrese la edad del usuario: ");
        int edad;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese una edad válida.");
                scanner.next();
            }
            edad = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer de entrada después de leer un entero
        } while (edad <= 0);
        return edad;
    }

    private String presentarOpcionesFitness() {
        System.out.println("Elija el objetivo de fitness:");
        System.out.println("1. Fuerza");
        System.out.println("2. Resistencia");
        System.out.println("3. Ganar Músculo");
        System.out.println("4. Perder Peso");
        return convertirOpcionAObjetivo(obtenerOpcionFitness());
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
            scanner.nextLine(); // Limpia el buffer de entrada después de leer un entero
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    private String convertirOpcionAObjetivo(int opcion) {
        switch (opcion) {
            case 1: return "Fuerza";
            case 2: return "Resistencia";
            case 3: return "Ganar Músculo";
            case 4: return "Perder Peso";
            default: return "Fuerza";
        }
    }

    private void mostrarEjercicioPorRangoEdad(Usuario usuario) {
        Ejercicio ejercicio = gestorEjercicios.obtenerEjercicioPorRangoEdad(usuario.getObjetivoFitness(), usuario.getEdad());
        if (ejercicio != null) {
            System.out.println("Ejercicio recomendado para " + usuario.getNombre() + " (" + usuario.getEdad() + " años): " + ejercicio.getNombre());
        } else {
            System.out.println("No se encontró ningún ejercicio recomendado para " + usuario.getNombre() + " (" + usuario.getEdad() + " años) con objetivo de fitness: " + usuario.getObjetivoFitness());
        }
    }

    private boolean preguntarSiContinuar() {
        System.out.println("¿Desea introducir otro usuario? (S/N)");
        String respuesta = scanner.nextLine().trim();
        return respuesta.equalsIgnoreCase("S");
    }

    private void enviarInformacionUsuario(Usuario usuario) {
        ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
        mensaje.setContent(usuario.getNombre() + ";" + usuario.getObjetivoFitness() + ";" + usuario.getEdad());
        mensaje.addReceiver(new AID("AgenteRecomendacion_Bienestar", AID.ISLOCALNAME));
        send(mensaje);
    }

    
}
