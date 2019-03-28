package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame implements ActionListener {
    //Text Field to display input
    private JTextField textField;

    //Button panel
    private JPanel btnPanel;

    //Text field panel
    private JPanel textPanel;

    private Calculator calculator;

    //Calculator buttons
    private JButton[] calcButtons = new JButton[buttonNames.length];
    private static final String[] buttonNames = {"C", "DEL", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "+", "-", "*", "/", "^", "(", ")", ".", "="};

    public CalculatorFrame(Calculator calc) {
        createUI();
        calculator = calc;
    }

    private void createUI() {
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.add(textField = new JTextField(30));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setEditable(false); //Prevent text field editing

        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5, 5));
        btnPanel.setBackground(Color.BLACK);

        //Add buttons to panel
        for(int i = 0; i < buttonNames.length; i++) {
            calcButtons[i] = new JButton(buttonNames[i]);
            calcButtons[i].addActionListener(this);
            btnPanel.add(calcButtons[i]);
        }

        add(btnPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.NORTH);

        setTitle("CALCULATOR");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program when window is closed
        setResizable(false); //User cannot edit text field
        setVisible(true); //Display window
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < buttonNames.length; i++) {
            if (e.getSource() == calcButtons[i]) {

                //If user clicks "=", begin evaluation
                if(calcButtons[i].getText().equals("="))
                    textField.setText(calculator.infixToPostfix(textField.getText()));

                //If user clicks "C", screen will be cleared
                else if(calcButtons[i].getText().equals("C"))
                    textField.setText("");

                //If user clicks "DEL", last entered button will be deleted
                else if(calcButtons[i].getText().equals("DEL"))
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));

                //Combine and display inputs on screen
                else
                    textField.setText(textField.getText().concat(buttonNames[i]));
            } //End if
        } //End for
    }
}
