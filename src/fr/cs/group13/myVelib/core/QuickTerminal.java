package fr.cs.group13.myVelib.core;

import fr.cs.group13.myVelib.core.system.VelibSystem;
import fr.cs.group13.myVelib.core.velibclui.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickTerminal {

    public static void main(String[] args) {
        new QuickTerminal();
    }

    public QuickTerminal() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }

            JFrame frame = new JFrame("VelibrTerminal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new ConsolePane());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public interface CommandListener {

        void commandOutput(String text);

        void commandCompleted(String cmd, int result);

        void commandFailed(Exception exp);
    }

    public class ConsolePane extends JPanel implements CommandListener, Terminal {

        private JTextArea textArea;
        private int userInputStart = 0;
        private Command cmd;
        private String prompt = "myvelib$ ";

        public ConsolePane() {

            cmd = new Command(this);

            setLayout(new BorderLayout());
            textArea = new JTextArea(20, 30);
            ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new ProtectedDocumentFilter(this));
            add(new JScrollPane(textArea));

            textArea.append(prompt);

            InputMap im = textArea.getInputMap(WHEN_FOCUSED);
            ActionMap am = textArea.getActionMap();

            Action oldAction = am.get("insert-break");
            am.put("insert-break", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int range = textArea.getCaretPosition() - userInputStart;
                    try {
                        String text = textArea.getText(userInputStart, range).trim();
                        if (text.startsWith(prompt)) {
                            text = text.substring(prompt.length()).trim();
                        }
                        System.out.println("[" + text + "]");
                        userInputStart += range;
                        if (!cmd.isRunning()) {
                            cmd.execute(text);
                        } else {
                            try {
                                cmd.send(text + "\n");
                            } catch (IOException ex) {
                                appendText("!! Failed to send command to process: " + ex.getMessage() + "\n");
                            }
                        }
                        textArea.append("\n");
                        userInputStart = textArea.getDocument().getLength();
                    } catch (BadLocationException ex) {
                        Logger.getLogger(QuickTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //oldAction.actionPerformed(e);
                }
            });

        }


        @Override
        public void commandOutput(String text) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(text + " ");
                updateUserInputPos();
            });
        }

        @Override
        public void commandFailed(Exception exp) {
            SwingUtilities.invokeLater(() -> {
                appendText("\nInvalid command. Type 'help' to see available commands.\n"+prompt);
            });
        }

        @Override
        public void commandCompleted(String cmd, int result) {
            SwingUtilities.invokeLater(() -> {
                appendText(prompt);
            });
        }

        protected void updateUserInputPos() {
            int pos = textArea.getCaretPosition();
            textArea.setCaretPosition(textArea.getText().length());
            userInputStart = pos;
        }

        @Override
        public int getUserInputStart() {
            return userInputStart;
        }

        @Override
        public void appendText(String text) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(text);
                updateUserInputPos();
            });
        }
    }

    public interface UserInput {

        public int getUserInputStart();
    }

    public interface Terminal extends UserInput {
        public void appendText(String text);
    }

    public class AppendTask implements Runnable {

        private Terminal terminal;
        private String text;

        public AppendTask(Terminal textArea, String text) {
            this.terminal = textArea;
            this.text = text;
        }

        @Override
        public void run() {
            terminal.appendText(text);
        }
    }

    public class Command {
        private String prompt = "myVelib$";
        private CommandListener listener;
        private ProcessRunner runner;

        public Command(CommandListener listener) {
            this.listener = listener;
        }

        public boolean isRunning() {

            return runner != null && runner.isAlive();

        }

        public void execute(String cmd) {

            String[] argsInt = cmd.split(" ");
            cmd = argsInt[0];
            String[] args = new String[argsInt.length -1];
            System.arraycopy(argsInt, 1, args, 0, args.length);
            String lowercaseCommand = cmd.toLowerCase();

            // Create a ByteArrayOutputStream to capture the output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Create a custom PrintStream that writes to the ByteArrayOutputStream
            PrintStream customPrintStream = new PrintStream(outputStream);

            // Redirect System.out to the custom PrintStream
            System.setOut(customPrintStream);

            switch (lowercaseCommand) {

                case "help":
                    displayHelp();
                    break;
                case "quit", "exit":
                    System.exit(0);
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
                    if (!cmd.trim().isEmpty()) {

                        List<String> values = new ArrayList<>(25);
                        if (cmd.contains("\"")) {

                            while (cmd.contains("\"")) {

                                String start = cmd.substring(0, cmd.indexOf("\""));
                                cmd = cmd.substring(start.length());
                                String quote = cmd.substring(cmd.indexOf("\"") + 1);
                                cmd = cmd.substring(cmd.indexOf("\"") + 1);
                                quote = quote.substring(0, cmd.indexOf("\""));
                                cmd = cmd.substring(cmd.indexOf("\"") + 1);

                                if (!start.trim().isEmpty()) {
                                    String parts[] = start.trim().split(" ");
                                    values.addAll(Arrays.asList(parts));
                                }
                                values.add(quote.trim());

                            }

                            if (!cmd.trim().isEmpty()) {
                                String parts[] = cmd.trim().split(" ");
                                values.addAll(Arrays.asList(parts));
                            }

                            for (String value : values) {
                                System.out.println("[" + value + "]");
                            }

                        } else {

                            if (!cmd.trim().isEmpty()) {
                                String parts[] = cmd.trim().split(" ");
                                values.addAll(Arrays.asList(parts));
                            }

                        }

                        runner = new ProcessRunner(listener, values);
            }
            }

            String capturedOutput = outputStream.toString();
            listener.commandOutput(capturedOutput);

            if (!cmd.equalsIgnoreCase("quit")) {
                listener.commandOutput("\n" + prompt);
            }


        }

        private void displayHelp() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/help.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    listener.commandOutput(line+"\n");
                }
                reader.close();
            } catch (IOException e) {
                listener.commandOutput("Error reading help file: "+ e);
            }
        }
        public void send(String cmd) throws IOException {
            runner.write(cmd);
        }
    }

    public class ProcessRunner extends Thread {

        private List<String> cmds;
        private CommandListener listener;

        private Process process;

        public ProcessRunner(CommandListener listener, List<String> cmds) {
            this.cmds = cmds;
            this.listener = listener;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println("cmds = " + cmds);
                ProcessBuilder pb = new ProcessBuilder(cmds);
                pb.redirectErrorStream();
                process = pb.start();
                StreamReader reader = new StreamReader(listener, process.getInputStream());
                // Need a stream writer...

                int result = process.waitFor();

                // Terminate the stream writer
                reader.join();

                StringJoiner sj = new StringJoiner(" ");
                cmds.stream().forEach((cmd) -> {
                    sj.add(cmd);
                });

                listener.commandCompleted(sj.toString(), result);
            } catch (Exception exp) {
                exp.printStackTrace();
                listener.commandFailed(exp);
            }
        }

        public void write(String text) throws IOException {
            if (process != null && process.isAlive()) {
                process.getOutputStream().write(text.getBytes());
                process.getOutputStream().flush();
            }
        }
    }

    public class StreamReader extends Thread {

        private InputStream is;
        private CommandListener listener;

        public StreamReader(CommandListener listener, InputStream is) {
            this.is = is;
            this.listener = listener;
            start();
        }

        @Override
        public void run() {
            try {
                int value = -1;
                while ((value = is.read()) != -1) {
                    listener.commandOutput(Character.toString((char) value));
                }
            } catch (IOException exp) {
                exp.printStackTrace();
            }
        }
    }

    public class ProtectedDocumentFilter extends DocumentFilter {

        private UserInput userInput;

        public ProtectedDocumentFilter(UserInput userInput) {
            this.userInput = userInput;
        }

        public UserInput getUserInput() {
            return userInput;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (offset >= getUserInput().getUserInputStart()) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (offset >= getUserInput().getUserInputStart()) {
                super.remove(fb, offset, length); //To change body of generated methods, choose Tools | Templates.
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (offset >= getUserInput().getUserInputStart()) {
                super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }
}