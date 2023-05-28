package fr.cs.Group13.myVelib.VelibCLUI;


import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class SetupCLUI {
    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("myVelib").build();
        parser.addArgument("velibnetworkName").help("The name of the myVelib network");
        parser.addArgument("--nstations").type(Integer.class).setDefault(10).help("The number of stations");
        parser.addArgument("--nslots").type(Integer.class).setDefault(10).help("The number of parking slots per station");
        parser.addArgument("--s").type(Double.class).setDefault(4.0).help("The side length of the station arrangement area");
        parser.addArgument("--nbikes").type(Integer.class).setDefault(0).help("The number of initial bikes in the network");

        try {
            Namespace namespace = parser.parseArgs(args);
            String velibnetworkName = namespace.getString("velibnetworkName");
            int nstations = namespace.getInt("nstations");
            int nslots = namespace.getInt("nslots");
            double s = namespace.getDouble("s");
            int nbikes = namespace.getInt("nbikes");

            // Implement the setup command logic with the parsed arguments
            System.out.println("Setup command executed with arguments:");
            System.out.println("velibnetworkName: " + velibnetworkName);
            System.out.println("nstations: " + nstations);
            System.out.println("nslots: " + nslots);
            System.out.println("s: " + s);
            System.out.println("nbikes: " + nbikes);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
