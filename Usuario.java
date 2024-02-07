package saludeduca;

public class Usuario {
    private String nombre;
    private String objetivoFitness;
    private int edad;

    public Usuario(String nombre, String objetivoFitness, int edad) {
        this.nombre = nombre;
        this.objetivoFitness = objetivoFitness;
        this.edad = edad;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getObjetivoFitness() {
        return objetivoFitness;
    }

    public int getEdad() {
        return edad;
    }
}
