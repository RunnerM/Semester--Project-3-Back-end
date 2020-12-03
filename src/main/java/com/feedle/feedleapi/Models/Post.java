package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Post {
    public int Id ;
    public String Title ;
    public String Context ;
    public String AuthorUserName ;
    public int Day ;
    public int Month ;
    public int Year ;
    public int Hour ;
    public int Minute ;
    public int Second ;
    //public byte[] images;
    public ArrayList<Comment> Comments ;
}
