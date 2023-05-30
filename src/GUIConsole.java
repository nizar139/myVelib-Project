import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.VelibCLUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUIConsole {
    private JTextArea textArea;
    private JTextField textField;

    public GUIConsole() {
        JFrame frame = new JFrame("GUI Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = textField.getText();
                textField.setText("");
                textArea.append("MyVelib$ " + command + "\n");
                processCommand(command);
            }
        });

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(textField, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Redirect the output stream to the text area
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void processCommand(String command) {
        // Split the command into command and arguments
        String[] argsInt = command.split(" ");
        String commandName = argsInt[0];
        String[] args = new String[argsInt.length - 1];
        System.arraycopy(argsInt, 1, args, 0, args.length);
        String lowercaseCommand = commandName.toLowerCase();

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
                    VelibSystem system = HandleSetupCommand.handleSetup(args);
                } catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "displaynetworks":
                try {
                    HandleDisplaySystemsCommand.handleDisplaySystems();
                }catch (Exception e){
                    System.out.println(e);
                }
                break;
            case "displaystations":
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
            case "displayuserinfo":
                try {
                    HandleDisplayUserInfoCommand.handleDisplayUserInfo(args);
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
                    HandleRentBikeCommand.HandleRent(args);
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "returnbike":
                try{
                    HandleReturnBikeCommand.HandleReturn(args);
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            case "displaystation":
                try{
                    HandleDisplayStationCommand.handleDisplayStation(args);
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
                    HandleDisplayUsersCommand.handleDisplayUsers(args);
                    HandleDisplayStationsCommand.handleDisplayStations(args);
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Invalid command. Type 'help' to see available commands.");
                break;

        }
    }

    private void displayHelp() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/help.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading help file: " + e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIConsole();
            }
        });
    }

    // CustomOutputStream to redirect the output stream to the text area
    private static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}