import jline.console.ConsoleReader;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.VelibCLUI.HandleSetup;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class VelibTerminal {
    public static void main(String[] args) {
        try {
            ConsoleReader console = new ConsoleReader();

            String line;
            while ((line = console.readLine("$ ")) != null) {
                // Ctrl+C was pressed
                if (line.isEmpty()) {
                    System.out.println("Ctrl+C was clicked. Terminal is shutting down...");
                    System.exit(0);
                }

                // Read the user input
                String[] args_int = line.trim().split(" ");
                String command = args_int[0];
                String[] commandArgs = new String[args_int.length - 1];
                System.arraycopy(args_int, 1, commandArgs, 0, commandArgs.length);
                String lowercaseCommand = command.toLowerCase();

                // Handle the command
                switch (lowercaseCommand) {
                    case "help":
                        displayHelp();
                        break;
                    case "quit":
                    case "exit":
                        System.exit(0);
                        break;
                    case "setup":
                        try {
                            VelibSystem system = HandleSetup.handleSetup(commandArgs);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    // Add cases for other commands
                    default:
                        System.out.println("Invalid command. Type 'help' to see available commands.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayHelp() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/help.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading help file: "+ e);
        }
    }
}
