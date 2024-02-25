package saludeduca;

public class Ejercicio {
    private String nombre;
    private String categoria;
    private String intensidad;
    private int duracion;
    private double rating;
    private int edadMinima;
    private int edadMaxima;
    private String descripcionCompleta;

    // Constructor con todos los atributos, incluyendo la descripción completa
    public Ejercicio(String nombre, String categoria, String intensidad, int duracion, double rating, int edadMinima, int edadMaxima, String descripcionCompleta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.intensidad = intensidad;
        this.duracion = duracion;
        this.rating = rating;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.descripcionCompleta = descripcionCompleta;
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

    public int getEdadMaxima() {
        return edadMaxima;
    }

    // Getter para la descripción completa
    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }
}
