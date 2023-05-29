import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.VelibCLUI.*;

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
            String[] argsInt = command.split(" ");// Read the user input
            command = argsInt[0];
            String[] args = new String[argsInt.length -1];
            System.arraycopy(argsInt, 1, args, 0, args.length);
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
                case "displaysystems":
                    try {
                        HandleDisplaySystemsCommand.handleDisplaySystems();
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displaynetworks":
                    try {
                        HandleDisplayStationsCommand.handleDisplayStations(args);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displayusers":
                    try {
                        HandleDisplayUsersCommand.handleDisplayUsers(args);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displaystationinfo":
                    try {
                        HandleDisplayStationInfoCommand.handleDisplayStationInfo(args);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case "adduser":
                    try{
                        HandleAddUserCommand.handleAddUser(args);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "offline":
                    try{
                        HandleOfflineCommand.handleOffline(args);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "online":
                    try{
                        HandleOnlineCommand.handleOnline(args);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "rentbike":
                    try{
                        HandleRentBikeCommand.HandleRent(args,0);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "returnbike":
                    try{
                        System.out.println("returnBike");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displaystation":
                    try{
                        System.out.println("displayStation");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "displayuser":
                    try{
                        HandleDisplayUserCommand.handleDisplayUser(args);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case "Sortstation":
                    try{
                        HandleSortStationCommand.handleSortStation(args);
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
