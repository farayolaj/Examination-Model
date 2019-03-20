package com.exam.examiner;

import java.util.ArrayList;

class Question {
    private String question;
    private Options options = null;

    Question(String ques){
        question = ques;
    }

    void addOption(ArrayList<String> opt){
        options = new Options(opt);
    }

    String getQuestion(){
        return question;
    }

    String getOptions(){
        return options.getOptions();
    }

    ArrayList<String> getArrOptions() { return options.arrOptions();}

    boolean hasOptions(){
        return options != null;
    }

}