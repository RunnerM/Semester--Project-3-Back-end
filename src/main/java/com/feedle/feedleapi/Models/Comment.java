package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class Comment {
    public int Id;
    public String Content;
    public int AuthorId;
    public String AuthorUserName;
    public int Day;
    public int Month;
    public int Year;
    public int Hour;
    public int Minute;
    public int Second;
}
