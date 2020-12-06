package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class Message {
    public int id;
    public String content;
    public int authorId;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;
    public int second;
}
