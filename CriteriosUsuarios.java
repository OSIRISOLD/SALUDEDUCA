package saludeduca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CriteriosUsuarios {
    private Scanner scanner;

    public CriteriosUsuarios(Scanner scanner) {
        this.scanner = scanner;
    }

    public void cargarRecomendaciones() {
        // Cargar recomendaciones desde el archivo Recomendaciones.txt
        try (Scanner fileScanner = new Scanner(new File("C:\\Users\\LuisS\\OneDrive\\Documentos\\NetBeansProjects\\SALUDEDUCA\\src\\saludeduca\\Recomendaciones.txt"))) {
            System.out.println("Recomendaciones de otros usuarios:");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las recomendaciones: " + e.getMessage());
        }
    }

    public void guardarRecomendacion(String ejercicio, String recomendacion) {
        // Guardar la nueva recomendación en el archivo Recomendaciones.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\LuisS\\OneDrive\\Documentos\\NetBeansProjects\\SALUDEDUCA\\src\\saludeduca\\Recomendaciones.txt", true))) {
            writer.println("Usuario: " + ejercicio + ", Recomendación: " + recomendacion);
        } catch (IOException e) {
            System.err.println("Error al guardar la recomendación: " + e.getMessage());
        }
    }
}
