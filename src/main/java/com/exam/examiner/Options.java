package com.exam.examiner;

import java.util.ArrayList;
import java.util.Iterator;

class Options {
    private ArrayList<String> options;

    Options(ArrayList<String> opt){
        options = opt;
    }

    String getOptions(){
        Iterator<String> iterator = options.iterator();
        StringBuilder ret = new StringBuilder();
        char ch = 'a';
        ret.append("\t").append(ch).append(". ").append(iterator.next());
        while(iterator.hasNext()){
            ch++;
            ret.append("      ").append(ch).append(". ").append(iterator.next());
        }
        return ret.toString();
    }

    ArrayList<String> arrOptions() {
        return options;
    }
}
