package saludeduca;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.Scanner;

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
            // Crear la instancia del scanner para entrada de usuario
            Scanner scanner = new Scanner(System.in);

            // Crear una instancia de CriteriosUsuarios
            CriteriosUsuarios criteriosUsuarios = new CriteriosUsuarios(scanner);

            // Crear y añadir el agente SALUDEDUCA con la instancia de CriteriosUsuarios
            Object[] arguments = { criteriosUsuarios };
            AgentController saludEducaAgent = mainContainer.createNewAgent(
                "SALUDEDUCA",
                "saludeduca.SALUDEDUCA",
                arguments
            );
            saludEducaAgent.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
