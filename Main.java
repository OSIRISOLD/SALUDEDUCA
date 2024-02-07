package saludeduca;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) {
        // Configurar el perfil para la plataforma JADE
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1098");
        profile.setParameter(Profile.GUI, "true");

        // Crear el contenedor principal
        Runtime runtime = Runtime.instance();
        AgentContainer mainContainer = runtime.createMainContainer(profile);

        try {
            // Crear y añadir el agente SALUDEDUCA
            AgentController saludEducaAgent = mainContainer.createNewAgent(
                "SALUDEDUCA",
                "saludeduca.SALUDEDUCA",
                null
            );
            saludEducaAgent.start();
            
            // Eliminar cualquier referencia a RecopilacionUsuarios o cualquier otro agente que no exista

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
