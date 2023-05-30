package fr.cs.group13.myVelib.core;

import fr.cs.group13.myVelib.core.system.VelibSystem;
import fr.cs.group13.myVelib.core.usecasescenario.ConfigReader;
import fr.cs.group13.myVelib.core.velibclui.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VelibTerminal {
    public static void main(String[] arguments) {

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
            System.out.println(lowercaseCommand);
            // Handle the command
            if (lowercaseCommand.equalsIgnoreCase("quit") || lowercaseCommand.equalsIgnoreCase("exit")){
                running = false;
            }else{
                executeCommand(lowercaseCommand,args);
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

    private static void runTestCommands(String testFile) {
        try {
            ConfigReader.Configuration();
            BufferedReader reader = new BufferedReader(new FileReader(testFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(" ");
                String command = args[0];
                String[] commandArgs = new String[args.length - 1];
                System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);
                executeCommand(command.toLowerCase(), commandArgs);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading test case file: "+ e);
        }
    }
    private static void executeCommand(String command, String[] args) {
        switch (command) {
            case "help":
                displayHelp();
                break;
            case "setup":
                try {
                    VelibSystem system = HandleSetupCommand.handleSetup(args);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displaynetworks":
                try {
                    HandleDisplaySystemsCommand.handleDisplaySystems();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displaystations":
                try {
                    HandleDisplayStationsCommand.handleDisplayStations(args);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displayusers":
                try {
                    HandleDisplayUsersCommand.handleDisplayUsers(args);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displaystationinfo":
                try {
                    HandleDisplayStationInfoCommand.handleDisplayStationInfo(args);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displayuserinfo":
                try {
                    HandleDisplayUserInfoCommand.handleDisplayUserInfo(args);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "adduser":
                try{
                    HandleAddUserCommand.handleAddUser(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "offline":
                try{
                    HandleOfflineCommand.handleOffline(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "online":
                try{
                    HandleOnlineCommand.handleOnline(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "rentbike":
                try{
                    HandleRentBikeCommand.HandleRent(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "returnbike":
                try{
                    HandleReturnBikeCommand.HandleReturn(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displaystation":
                try{
                    HandleDisplayStationCommand.handleDisplayStation(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "displayuser":
                try{
                    HandleDisplayUserCommand.handleDisplayUser(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "sortstation":
                try{
                    HandleSortStationCommand.handleSortStation(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "display":
                try{
                    HandleDisplayUsersCommand.handleDisplayUsers(args);
                    HandleDisplayStationsCommand.handleDisplayStations(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "planride":
                try{
                    HandlePlanRideCommand.handlePlanRide(args);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "runtest":
                if (args.length == 1) {
                    String testFile = args[0];
                    runTestCommands(testFile);
                } else {
                    System.out.println("Invalid command. Please provide the test case file.");
                }
                break;
            default:
                System.out.println("Invalid command. Type 'help' to see available commands.");
                break;
        }
    }
}
