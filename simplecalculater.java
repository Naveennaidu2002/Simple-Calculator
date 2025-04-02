import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class C{
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create the display field
        JTextField displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Variables for calculation
        final String[] operator = {null};
        final double[] operand1 = {0};

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = ((JButton) e.getSource()).getText();

                    if ("0123456789".contains(text)) {
                        displayField.setText(displayField.getText() + text);
                    } else if ("C".equals(text)) {
                        displayField.setText("");
                        operator[0] = null;
                        operand1[0] = 0;
                    } else if ("=".equals(text)) {
                        if (operator[0] != null) {
                            double operand2 = Double.parseDouble(displayField.getText());
                            double result = 0;
                            switch (operator[0]) {
                                case "+": result = operand1[0] + operand2; break;
                                case "-": result = operand1[0] - operand2; break;
                                case "*": result = operand1[0] * operand2; break;
                                case "/": 
                                    if (operand2 != 0) {
                                        result = operand1[0] / operand2;
                                    } else {
                                        displayField.setText("Error");
                                        return;
                                    }
                                    break;
                            }
                            displayField.setText(String.valueOf(result));
                            operator[0] = null;
                        }
                    } else { // Operators: +, -, *, /
                        if (!displayField.getText().isEmpty()) {
                            operand1[0] = Double.parseDouble(displayField.getText());
                            operator[0] = text;
                            displayField.setText("");
                        }
                    }
                }
            });
            buttonPanel.add(button);
        }

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(displayField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
