import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MainWindow {
    static Map<String, JLabel> displays = new HashMap<>();

    public void createWindow() {
        Calculator values = new Calculator();

        // === | frame settings
        JFrame frame = new JFrame("Calculator"); // Create the frame and set the title
        frame.setSize(385, 590); // width and weight
        frame.setLocationRelativeTo(null); // start in center of screen
        frame.setLayout(null); // disable the layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop the operation when press the "x" button


        // === | declaring the used colors
        Color backgroundColor = new Color(18, 18, 20);
        Color displayColor = new Color(26, 26, 30);
        Color buttonColor = new Color(26, 26, 30);
        Color accentButtonColor = new Color(21, 80, 39);
        Color accentColor = new Color(0, 202, 61);

        // === | frame style
        frame.getContentPane().setBackground(backgroundColor);

        // == | buttons

        // button number 1
        JButton number1 = new JButton("1");
        number1.setBounds(10, 370, 80, 80);

        // button number 2
        JButton number2 = new JButton("2");
        number2.setBounds(100, 370, 80, 80);

        // button number 3
        JButton number3 = new JButton("3");
        number3.setBounds(190, 370, 80, 80);

        // button number 4
        JButton number4 = new JButton("4");
        number4.setBounds(10, 280, 80, 80);

        // button number 5
        JButton number5 = new JButton("5");
        number5.setBounds(100, 280, 80, 80);

        // button number 6
        JButton number6 = new JButton("6");
        number6.setBounds(190, 280, 80, 80);

        // button number 7
        JButton number7 = new JButton("7");
        number7.setBounds(10, 190, 80, 80);

        // button number 8
        JButton number8 = new JButton("8");
        number8.setBounds(100, 190, 80, 80);

        // button number 9
        JButton number9 = new JButton("9");
        number9.setBounds(190, 190, 80, 80);

        // button number 0
        JButton number0 = new JButton("0");
        number0.setBounds(100, 460, 80, 80);

        // button double 0
        JButton number00 = new JButton("00");
        number00.setBounds(10, 460, 80, 80);

        // == | operation buttons

        // button decimal number
        JButton dot = new JButton(".");
        dot.setBounds(190, 460, 80, 80);

        // button get result
        JButton equal = new JButton("=");
        equal.setBounds(280, 370, 80, 170);
        equal.setBackground(accentColor);
        frame.add(equal);
        equal.addActionListener(e -> {
            values.getResult();
        });

        // button addition
        JButton addition = new JButton("+");
        addition.setBounds(280, 190, 80, 80);

        // button subtraction
        JButton subtraction = new JButton("-");
        subtraction.setBounds(280, 280, 80, 80);

        // button multiplication
        JButton multiplication = new JButton("X");
        multiplication.setBounds(280, 100, 80, 80);

        // button division
        JButton division = new JButton("÷");
        division.setBounds(190, 100, 80, 80);

        // button module
        JButton module = new JButton("%");
        module.setBounds(100, 100, 80, 80);

        // button clear
        JButton clear = new JButton("CE");
        clear.setBounds(10, 100, 80, 80);
        clear.addActionListener(e -> {
            values.clearDisplay();
        });

        // == | displays

        // top display
        JLabel displayTop = new JLabel("");
        displayTop.setBounds(10, 10, 350, 25);
        displayTop.setOpaque(true);
        displayTop.setBackground(displayColor);
        displayTop.setForeground(Color.white);
        frame.add(displayTop);

        displays.put("top", displayTop);

        // inferior display
        JLabel displayBottom = new JLabel(" 0");
        displayBottom.setBounds(10, 35, 350, 55);
        displayBottom.setOpaque(true);
        displayBottom.setBackground(displayColor);
        displayBottom.setFont(new Font("Arial", Font.BOLD, 24));
        displayBottom.setForeground(Color.white);
        frame.add(displayBottom);

        displays.put("bottom", displayBottom);

        // add components and insert style

        List<JButton> windowNumberButtons = new ArrayList<>(Arrays.asList(
                number7, number8, number9,
                number4, number5, number6,
                number1, number2, number3,
                number00, number0, dot
        ));


        for (JButton windowNumberButton : windowNumberButtons) {
            windowNumberButton.setBackground(buttonColor);
            windowNumberButton.setForeground(Color.white);
            frame.add(windowNumberButton);
            windowNumberButton.addActionListener(e -> {
                values.insertNumber(windowNumberButton.getText());
            });
        }

        List<JButton> windowOperationButtons = new ArrayList<>(Arrays.asList(
                clear, module, division,
                multiplication, addition, subtraction
        ));

        for (JButton windowOperationButton : windowOperationButtons) {
            windowOperationButton.setBackground(accentButtonColor);
            windowOperationButton.setForeground(Color.white);
            frame.add(windowOperationButton);
            windowOperationButton.addActionListener(e -> {
                values.insertOperation(windowOperationButton.getText());
            });
        }

        // show the screen
        frame.setVisible(true);
    }

    public void updateTopDisplay(String value) {
        displays.get("top").setText(" " + value);
    }

    public void updateBottomDisplay(String value) {
        displays.get("bottom").setText(" " + value);
    }
}
