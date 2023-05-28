import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.VelibCLUI.HandleSetupCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VelibTerminal {
    public static void main(String[] arguments) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Ctrl+C was clicked. Terminal is shutting down...")));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("$ "); // Display the prompt symbol
            String command = scanner.nextLine().trim();
            String[] args_int = command.split(" ");// Read the user input
            command = args_int[0];
            String[] args = new String[args_int.length -1];
            System.arraycopy(args_int, 1, args, 0, args.length);
            String lowercaseCommand = command.toLowerCase();

            // Handle the command
            switch (lowercaseCommand) {
                case "help":
                    displayHelp();
                    break;
                case "quit", "exit":
                    running = false;
                    break;
                case "setup":
                    try {
                        VelibSystem system = HandleSetupCommand.handleSetup(args);
                    } catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "addUser":
                    try{
                        System.out.println("working");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "offline":
                    try{
                        System.out.println("offline");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "online":
                    try{
                        System.out.println("online");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "rentBike":
                    try{
                        System.out.println("rentBike");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "returnBike":
                    try{
                        System.out.println("returnBike");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displayStation":
                    try{
                        System.out.println("displayStation");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displayUser":
                    try{
                        System.out.println("displayUser");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "SortStation":
                    try{
                        System.out.println("SortStation");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "display":
                    try{
                        System.out.println("display");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' to see available commands.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Terminal closed. Goodbye!");
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
