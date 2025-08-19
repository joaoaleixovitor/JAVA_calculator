import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final MainWindow frame = new MainWindow();

    private String topDisplayValue = "";
    private String bottomDisplayValue = "0";

    private final List<Character> expressionOperators = new ArrayList<>();
    private final List<String> expressionNumbers = new ArrayList<>();
    private boolean isTheResult = false;

    public void insertNumber (String numberEntered) {
        if (isTheResult) {
            isTheResult = false;

            double value = Double.parseDouble(bottomDisplayValue);
            if (value != 0) {
                topDisplayValue = bottomDisplayValue.concat(" + ");
                expressionOperators.add('+');
            }

            bottomDisplayValue = numberEntered;
        } else {
            if (numberEntered.equals(".")) {
                if (!bottomDisplayValue.contains(".")) {
                    if (!bottomDisplayValue.equals("-")) {
                        bottomDisplayValue = bottomDisplayValue.concat(numberEntered);
                    }
                }
            } else {
                if (!bottomDisplayValue.equals("-")) {
                    double value = Double.parseDouble(bottomDisplayValue);
                    double number = Double.parseDouble(numberEntered);
                    if (bottomDisplayValue.substring(bottomDisplayValue.length() - 1).equals(".")) {
                        bottomDisplayValue = bottomDisplayValue.concat(numberEntered);
                    } else {
                        if (value == 0) {
                            if (!bottomDisplayValue.contains(".")) {
                                if (number != 0) {
                                    bottomDisplayValue = numberEntered;
                                }
                            } else {
                                bottomDisplayValue = bottomDisplayValue.concat(numberEntered);
                            }
                        } else {
                            bottomDisplayValue = bottomDisplayValue.concat(numberEntered);
                        }
                    }
                } else {
                    bottomDisplayValue = bottomDisplayValue.concat(numberEntered);
                }
            }
        }
        updateDisplay();
    }

    public void insertOperation(String button) {
        if (!button.equals("CE") && !bottomDisplayValue.equals("-")) {
            if (isTheResult) {
                isTheResult = false;

                double value = Double.parseDouble(bottomDisplayValue);
                if (value != 0) {
                    topDisplayValue = bottomDisplayValue.concat(" " + button + " ");
                    expressionOperators.add(button.charAt(0));
                }

                bottomDisplayValue = "0";
            } else {
                double value = Double.parseDouble(bottomDisplayValue);

                if (value == 0) {
                    if (button.equals("-")) {
                        bottomDisplayValue = button;
                    }
                } else {
                    topDisplayValue = topDisplayValue.concat(bottomDisplayValue).concat(" " + button + " ");
                    expressionOperators.add(button.charAt(0));
                    expressionNumbers.add(bottomDisplayValue);
                    bottomDisplayValue = "0";
                }
            }
            updateDisplay();
        }
    }

    public void getResult() {
        if (!bottomDisplayValue.equals("-")) {
            double value = Double.parseDouble(bottomDisplayValue);
            if (!expressionOperators.isEmpty()) {
                if (value != 0) {
                    topDisplayValue = topDisplayValue.concat(bottomDisplayValue);
                    expressionNumbers.add(bottomDisplayValue);
                    bottomDisplayValue = "0";

                    while (expressionOperators.contains('X') || expressionOperators.contains('÷') || expressionOperators.contains('%')) {
                        for (int i = 0; i < expressionOperators.size(); i++) {
                            String operator = String.valueOf(expressionOperators.get(i));
                            if (operator.equals("X") || operator.equals("÷") || operator.contains("%")) {
                                double num1 = Double.parseDouble(expressionNumbers.get(i));
                                double num2 = Double.parseDouble(expressionNumbers.get(i + 1));
                                double result = operator.equals("X") ? num1 * num2 :
                                                operator.equals("÷") ? num1 / num2 : num1 % num2;

                                expressionNumbers.set(i, String.valueOf(result));
                                expressionNumbers.remove(i + 1);
                                expressionOperators.remove(i);
                                break;
                            }
                        }
                    }

                    while (!expressionOperators.isEmpty()) {
                        String operator = String.valueOf(expressionOperators.getFirst());
                        double num1 = Double.parseDouble(expressionNumbers.get(0));
                        double num2 = Double.parseDouble(expressionNumbers.get(1));
                        double result = operator.equals("+") ? num1 + num2 : num1 - num2;

                        expressionNumbers.set(0, String.valueOf(result));
                        expressionNumbers.remove(1);
                        expressionOperators.removeFirst();
                    }

                    bottomDisplayValue = expressionNumbers.getFirst();

                    if (Double.parseDouble(bottomDisplayValue) % 1 == 0) {
                        bottomDisplayValue = bottomDisplayValue.substring(0, bottomDisplayValue.indexOf("."));
                    }
                }
                isTheResult = true;
            }
        }

        updateDisplay();
    }

    public void clearDisplay() {
        topDisplayValue = "";
        bottomDisplayValue = "0";

        expressionOperators.clear();
        expressionNumbers.clear();

        isTheResult = false;

        updateDisplay();
    }

    private void updateDisplay() {
        frame.updateTopDisplay(topDisplayValue);
        frame.updateBottomDisplay(bottomDisplayValue);
    }
}
