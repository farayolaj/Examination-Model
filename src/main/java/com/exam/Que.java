package com.exam;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Que {
    private static Grade grade;
    private static String subject;

    public static void main(String[] args) {
        clInterface();
        try {
            grade.jsonifySubject(subject, new FileWriter(new File("file.json")));
            grade.printSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clInterface() {
        boolean running = true;
        try(Scanner sc = new Scanner(System.in)) {

            System.out.print("Please, input your name with title attached: ");
            System.out.println("\nWelcome, " + sc.nextLine() + ". Please, enter the class name.");
            Grade grade = new Grade(sc.nextLine());
            Que.grade = grade;
            System.out.println("We also need the subject you'll be setting questions on.");
            String sub = sc.nextLine();
            Que.subject = sub;
            grade.addSubject(sub);
            System.out.println("\nNow, you can begin setting the questions.");
            System.out.println("Precede a question with \"Q\" and an option with \"O\".\nWhen you're done with a question, enter \"N\".");
            System.out.println("When you're done with all questions, enter \"E\".");

            while (running) {
                boolean inRunning = true;
                String question = null;
                ArrayList<String> options = new ArrayList<>();

                while(inRunning) {
                    String input = sc.next();

                    switch(input.toLowerCase()){
                        case "q" :
                            question = sc.nextLine().trim();
                            //System.out.println("Question added");
                            if(!options.isEmpty()) {
                                options.clear();
                                //System.out.println("Options cleared");
                            }
                            break;

                        case "o" :
                            options.add(sc.nextLine().trim());
                            //System.out.println("Option added");
                            //System.out.println("Size of options: " + options.size());
                            break;

                        case "n" :
                            inRunning = false;
                            //System.out.println("Inner loop stopped");
                            break;

                        case "e" :
                            inRunning = false;
                            running = false;
                            //System.out.println("Both loops stopped");
                            break;
                    }
                }

                if (question != null) {
                    if (options.isEmpty()) {
                        grade.getSubject(sub).add(question);
                        //System.out.println("Question added, size of question: " + grade.getSubject(sub).sizeOfQuestion());
                    } else {
                        grade.getSubject(sub).add(question, options);
                        //System.out.println("Question and its options added, size of question: " + grade.getSubject(sub).sizeOfQuestion());
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}