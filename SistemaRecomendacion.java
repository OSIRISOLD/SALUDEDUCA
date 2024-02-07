package saludeduca;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import jade.core.behaviours.Behaviour;

public class SistemaRecomendacion extends Agent {
    private static final String RUTA_ARCHIVO_RECOMENDACIONES = "C:\\Users\\LuisS\\OneDrive\\Documentos\\NetBeansProjects\\SALUDEDUCA\\src\\saludeduca\\Recomendaciones.txt";
    private HashMap<String, String> recomendacionesPorEjercicio;

    @Override
    protected void setup() {
        cargarRecomendaciones();
        addBehaviour(new RecibirSolicitudesRecomendacion());
    }

    private class RecibirSolicitudesRecomendacion extends Behaviour {
        @Override
        public void action() {
            ACLMessage mensaje = receive();
            if (mensaje != null && mensaje.getContent().startsWith("EJERCICIO:")) {
                String ejercicio = mensaje.getContent().split(":")[1].trim();
                String recomendacion = recomendacionesPorEjercicio.getOrDefault(ejercicio, "No hay recomendación para este ejercicio.");
                enviarRecomendacion(mensaje.getSender(), recomendacion);
            } else {
                block();
            }
        }

        @Override
        public boolean done() {
            return false;
        }

        private void enviarRecomendacion(AID receptor, String recomendacion) {
            ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
            mensaje.setContent(recomendacion);
            mensaje.addReceiver(receptor);
            send(mensaje);
        }
    }

    private void cargarRecomendaciones() {
        recomendacionesPorEjercicio = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO_RECOMENDACIONES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(", ");
                if (partes.length == 3) {
                    String ejercicio = partes[0].split(": ")[1].trim();
                    String recomendacion = partes[2].split(": ")[1].trim();
                    recomendacionesPorEjercicio.put(ejercicio, recomendacion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
