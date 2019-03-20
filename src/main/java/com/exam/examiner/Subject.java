package com.exam.examiner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class abstracts the subjects offered in each a class.
 *
 * @author FarayolaJ
 */
public class Subject {
    private String subject; // holds the name of the subject
    private HashMap<Integer, Question> questions; // contains the questions
    private int noq = 0;// number of questions added

    public Subject() {

    }

    /**
     * Creates an empty instance of {@link Subject}
     *
     * @param sub the name of the subject
     */
    public Subject(String sub) {
        subject = sub;
        questions = new HashMap<>();
    }

    /**
     * Creates an instance of {@link Subject} from a saved JSON file
     *
     * @param reader a reader that reads from a JSON file
     */
    /*public Subject(Reader reader) throws IllegalArgumentException {
        JSONTokener tokener = new JSONTokener(reader);
        parse(tokener);
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static Subject parse(String file) {
        try (FileReader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject obj = new JSONObject(tokener);
            Subject sub = new Subject(obj.getString("subject"));
//            HashMap<Integer, Question> subQues = new HashMap<>();
            JSONArray ques = obj.getJSONArray("questions");
            for (Object q : ques) {
                JSONObject ob = (JSONObject) q;
                ArrayList<String> options = new ArrayList<>();
                for (Object opt : ob.getJSONArray("options")) {
                    String temp = (String) opt;
                    options.add(temp);
                }
                sub.add(ob.getString("question"), options);
            }
            return sub;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // adds new questions only to the subject
    public void add(String ques) {
        Question question = new Question(ques);
        questions.put(noq, question);
        noq++;
    }

    // adds new questions and their options to the subject
    public void add(String ques, ArrayList<String> opt) {
        Question question = new Question(ques);
        question.addOption(opt);
        questions.put(noq, question);
        noq++;
    }

    /**
     * Prints the result of (@code stringify()) to the standard output;
     */
    public void print() {
        System.out.println(stringify());
    }

    /**
     * @return a structured representation of the questions under the subject as a string
     */
    public String stringify() {
        StringBuilder sb = new StringBuilder();
        sb.append(subject.toUpperCase()).append("\n");

        for (int j = 0; j < noq; j++) {
            Question question = questions.get(j);
            sb.append(j + 1).append(".\t");
            sb.append(question.getQuestion()).append("\n");
            if (question.hasOptions()) sb.append(question.getOptions()).append("\n");
        }

        return sb.toString();
    }

    // writes to a json file
    public void writeJSON(Writer w) {
        JSONWriter jwrite;
        jwrite = new JSONWriter(w);
        jwrite.object()
                .key("subject")
                .value(subject)
                .key("questions")
                .value(
                        arrayofQuestions()
                )
                .endObject();
    }

    public void jsonWithSpacing() {
        try {
            JSONObject ob = new JSONObject().put("subject", subject).put("questions", arrayofQuestions());
            FileWriter fw = new FileWriter("file2.json");
            ob.write(fw, 4, 0);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray arrayofQuestions() {
        JSONArray arr = new JSONArray();
        for (int j = 0; j < questions.size(); j++) {
            JSONObject temp = new JSONObject();
            if (questions.get(j).hasOptions()) {
                temp.put("question", questions.get(j).getQuestion()).put("options",
                        new JSONArray(questions.get(j).getArrOptions()));
            } else {
                temp.put("question", questions.get(j).getQuestion()).put("options", new JSONArray());
            }
            arr.put(temp);
        }
        return arr;
    }

    //returns number of questions
    /*public int sizeOfQuestion() {
        return questions.size();
    }*/

    // returns subject name
    /*String getSubName() {
        return subject;
    }*/

}