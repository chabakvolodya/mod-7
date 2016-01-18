package com.goit.module7;

import com.goit.module7.utils.DB.DB;
import com.goit.module7.utils.DB.GRUD;
import com.goit.module7.utils.ReversePolandArticle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Created by Администратор on 18.01.2016.
 */
public class Calc {

    private static Connection connection = DB.getConnection();
    private static GRUD sessionDB = new GRUD(connection);
    private static String NAME_TABLE = "result";

    public static void run() throws IOException{

        boolean status = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        sessionDB.createTable(NAME_TABLE);

        while (status){
            System.out.println("Ввід:");

            String expression = reader.readLine().trim();

            switch (expression){
                case "help":
                    System.out.println("history - [Перегляд історії обчислень]");
                    System.out.println("clear - [Очищення історії обчислень]");
                    System.out.println("exit - [Вихід з каркулятора]");
                    break;

                case "history":
                    getHistoryResultInDB(NAME_TABLE); break;

                case "clear":
                    clear(NAME_TABLE);
                    break;

                case "exit":
                    exit(NAME_TABLE);
                    status = false;
                    break;

                default:
                    double result = ReversePolandArticle.resultExpression(expression);
                    System.out.println("Результат: " + result + "\n");
                    addResultToDB(NAME_TABLE, result);
            }

        }
    }

    private static void addResultToDB(String nameTable, double result){

        sessionDB.insert(nameTable, result);
    }

    private static void getHistoryResultInDB(String nameTable){

        sessionDB.select(nameTable);
    }

    private static void clear(String nameTable){

        sessionDB.deleteTable(nameTable);
        sessionDB.createTable(nameTable);
    }

    private static void exit(String nameTable){

        sessionDB.deleteTable(nameTable);
        DB.closeConnection();
    }
}

