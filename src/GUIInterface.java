import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUIInterface {
    private JTextArea textArea;

    public GUIInterface() {
        JFrame frame = new JFrame("GUI Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton setupButton = new JButton("Setup");
        setupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeCommand("setup");
            }
        });

        JButton displayNetworksButton = new JButton("Display Networks");
        displayNetworksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeCommand("displaynetworks");
            }
        });

        JButton displayStationsButton = new JButton("Display Stations");
        displayStationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeCommand("displaystations");
            }
        });

        buttonPanel.add(setupButton);
        buttonPanel.add(displayNetworksButton);
        buttonPanel.add(displayStationsButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Redirect the output stream to the text area
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private void executeCommand(String command) {
        textArea.append("Command: " + command + "\n");
        switch (command) {
            case "setup":
                // Handle setup command
                break;
            case "displaynetworks":
                // Handle display networks command
                break;
            case "displaystations":
                // Handle display stations command
                break;
            // Add more commands and their handling here...
            default:
                textArea.append("Invalid command. Type 'help' to see available commands.\n");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIInterface();
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
