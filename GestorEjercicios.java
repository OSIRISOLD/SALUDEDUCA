package saludeduca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestorEjercicios {
    private HashMap<String, ArrayList<Ejercicio>> ejerciciosPorCategoria;
    private String rutaArchivo;

    // Constructor que acepta la ruta del archivo como parámetro
    public GestorEjercicios(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        ejerciciosPorCategoria = new HashMap<>();
        cargarEjerciciosDesdeArchivo();
    }

    private void cargarEjerciciosDesdeArchivo() {
        File archivo = new File(rutaArchivo);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    if (partes.length == 8) {
                        String nombre = partes[0];
                        String categoria = partes[1];
                        String intensidad = partes[2];
                        int duracion = Integer.parseInt(partes[3]);
                        double rating = Double.parseDouble(partes[4]);
                        int edadMinima = Integer.parseInt(partes[5]);
                        int edadMaxima = Integer.parseInt(partes[7]);
                        Ejercicio ejercicio = new Ejercicio(nombre, categoria, intensidad, duracion, rating, edadMinima, edadMaxima);
                        ejerciciosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(ejercicio);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }
    }

    public Ejercicio obtenerEjercicioPorRangoEdad(String objetivoFitness, int edad) {
        ArrayList<Ejercicio> ejerciciosCategoria = ejerciciosPorCategoria.get(objetivoFitness);
        if (ejerciciosCategoria != null) {
            for (Ejercicio ejercicio : ejerciciosCategoria) {
                if (edad >= ejercicio.getEdadMinima() && edad <= ejercicio.getEdadMaxima()) {
                    return ejercicio; // Retorna el primer ejercicio que cumpla con el rango de edad
                }
            }
        }
        return null; // No se encontró un ejercicio adecuado
    }
    
    // Otros métodos necesarios...
}
