package com.example.jerry.notetaker;

import java.util.Date;

public class Joy extends Emotion {

    String emotionName = "Joy";
    String comment;
    Date date;

    // Setters
    public void setComment(String comment) {
        if (comment.length() <= 100){
            this.comment = comment;
        }
        else{
        }
    }

    public void setDate(Date date){
        this.date = date;
    }


    // Getters
    public Date getDate(){
        return this.date;
    }

    public String getComment(){
        return this.comment;
    }

    public String getEmotionName(){
        return this.emotionName;
    }
}
