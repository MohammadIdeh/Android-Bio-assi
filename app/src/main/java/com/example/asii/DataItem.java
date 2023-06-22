package com.example.asii;

public class DataItem {
    private String answer1;
    private String answer2;
    private String answer3;
    private int result;

    public DataItem(String answer1, String answer2, String answer3, int result) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.result = result;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public int getResult() {
        return result;
    }
}