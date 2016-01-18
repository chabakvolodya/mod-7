package com.goit.module7.utils;

import java.util.Stack;

/**
 * Created by Администратор on 17.01.2016.
 */
public class ReversePolandArticle {

    private static String operatorsArray[] = {"\\+", "\\-", "\\*", "\\/", "sin", "cos", "\\(", "\\)"};

    public static double resultExpression(String expression){

        return deCode(code(expression));
    }

    public static double deCode(String str){
        double result = 0;

        String arrayStrings[] = str.split("\\s");
        Stack<String> stackOperand = new Stack<String>();

        for (int i = 0; i < arrayStrings.length; i++){

            if (exist(arrayStrings[i])){ // True якщо елемент == оператору

                if (stackOperand.size() > 1){
                    double rightOperand = Double.parseDouble(stackOperand.pop());
                    double leftOperand = Double.parseDouble(stackOperand.pop());

                    switch (arrayStrings[i]){
                        case "+": result = leftOperand + rightOperand; break;
                        case "-": result = leftOperand - rightOperand; break;
                        case "*": result = leftOperand * rightOperand; break;
                        case "/": result = leftOperand / rightOperand; break;
                        case "sin": result = Math.sin(rightOperand); break;
                        case "cos": result = Math.cos(rightOperand); break;
                        case "tan": result = Math.tan(rightOperand); break;
                    }
                }
                else {
                    double rightOperand = Double.parseDouble(stackOperand.pop());

                    switch (arrayStrings[i]){
                        case "sin": result = Math.sin(rightOperand); break;
                        case "cos": result = Math.cos(rightOperand); break;
                        case "tan": result = Math.tan(rightOperand); break;
                    }

                }
                stackOperand.push(String.valueOf(result));
            }
            else {
                stackOperand.push(arrayStrings[i]);
            }

        }
        result = Double.parseDouble(stackOperand.pop());    //Останній елемент результат вираження
        return result;
    }

    public static String code(String str){

        String result = "";
        Stack<String> stackOperand = new Stack<String>();

        str = str.replaceAll("\\s", "");

        str = replaceOperators(operatorsArray, str).trim();
        String arrayStrings[] = str.split("\\s");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < arrayStrings.length; i++){  //Перебір массиву строк

            if(exist(arrayStrings[i])){ // True якщо елемент == оператору

                if (arrayStrings[i].equals(")")) {   //Якщо оператор == ")"

                    int firstIndex = 0; //Резервуєм змінну де буде початковий індекс оператора "(" у стеку

                    for (int k = stackOperand.size() - 1; k >= 0; k--){  //Знаходимо індекс оператора "(", відкиваючу дужку
                        if (stackOperand.get(k).equals("(")){
                            firstIndex = k;
                            break;
                        }
                    }
//                    System.out.println("firstIndex = " + firstIndex);   //[TEST] індекс закриваючої дужки

                    for (int j = stackOperand.size()-1; j >= firstIndex; j--){

                        String temp = "";

                        if (stackOperand.peek().equals("(")){
                            stackOperand.pop(); //Видалаємо з стеку оператор "("
                            break;
                        }
                        temp = stackOperand.pop();
                        result = result + " " + temp;
                    }
                }
                else {
                    stackOperand.push(arrayStrings[i]);  //Кладем оператор в стек
                }
            }
            else {
                result = result + " " + arrayStrings[i].replaceAll("\\\\", "");
            }

            if (i == arrayStrings.length - 1){
                for (int j = stackOperand.size()-1; j >= 0; j--){
                    result = result + " " + stackOperand.get(j);
                    stackOperand.removeElementAt(j);
                }
            }
        }

//        System.out.println(result); //[TEST] - Вивід польского запису
        result = result.trim();

        return result;

    }

    private static String replaceOperators(String[] operators, String str){

        for (int i = 0; i < operators.length; i++) {
            str = str.replaceAll(operators[i], " " + operators[i] + " ");
        }
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    private static boolean exist(String var){
        boolean status = false;
        String operator;

        for (int i = 0; i < operatorsArray.length; i++){

            operator = operatorsArray[i].replaceAll("\\\\", "");
            if (var.equals(operator)) {
                status = true;
                return status;
            }
        }
        return status;
    }

}
