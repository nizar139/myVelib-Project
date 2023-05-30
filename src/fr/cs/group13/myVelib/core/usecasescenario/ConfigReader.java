package fr.cs.group13.myVelib.core.usecasescenario;

import fr.cs.group13.myVelib.core.velibclui.HandleDisplaySystemsCommand;
import fr.cs.group13.myVelib.core.system.VelibGlobal;
import fr.cs.group13.myVelib.core.system.VelibSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE = "eval/my_velib.ini";

    public static void Configuration() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String networkName = properties.getProperty("networkName");
        int nStations = Integer.parseInt(properties.getProperty("nStations"));
        int nPlusStations = Integer.parseInt(properties.getProperty("nPlusStations"));
        int nSlotsperStation = Integer.parseInt(properties.getProperty("nSlotsperStation"));
        float bicycleratio = Float.parseFloat(properties.getProperty("bicycleratio"));
        float electricalratio = Float.parseFloat(properties.getProperty("electricalratio"));
        int side = Integer.parseInt(properties.getProperty("side"));


        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem velibSystem = new VelibSystem(networkName);
        velibSystem.generateStationMap(nStations,nPlusStations,nSlotsperStation,1-bicycleratio,electricalratio,side,side);
        instance.addVelibSystem(velibSystem);
        HandleDisplaySystemsCommand.handleDisplaySystems();
    }
}
