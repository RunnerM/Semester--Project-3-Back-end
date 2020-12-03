package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class Message {
    public int Id;
    public String Content;
    public int AuthorId;
    public int Day;
    public int Month;
    public int Year;
    public int Hour;
    public int Minute;
    public int Second;
}
