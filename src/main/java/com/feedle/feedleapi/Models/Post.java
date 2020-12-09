package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Post {
    public int id ;
    public String title ;
    public String content ;
    public String authorUserName ;
    public int day ;
    public int month ;
    public int year ;
    public int hour ;
    public int minute ;
    public int second ;
    //public byte[] images;
    public ArrayList<Comment> comments ;
    public int approvals;
    public int disapprovals;
}
