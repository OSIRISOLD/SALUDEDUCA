package saludeduca;

public class Ejercicio {
    private String nombre;
    private String categoria;
    private String intensidad;
    private int duracion;
    private double rating;
    private int edadMinima;
    private int edadMaxima; // Atributo añadido para la edad máxima

    // Constructor con todos los atributos, incluyendo la edad máxima
    public Ejercicio(String nombre, String categoria, String intensidad, int duracion, double rating, int edadMinima, int edadMaxima) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.intensidad = intensidad;
        this.duracion = duracion;
        this.rating = rating;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    // Getters para todos los atributos
    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getRating() {
        return rating;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() { // Getter para la edad máxima
        return edadMaxima;
    }

    // Aquí podrías añadir setters si necesitas modificar los atributos después de crear el objeto
}
