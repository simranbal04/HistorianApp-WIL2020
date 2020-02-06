package com.simrankaurbal.historian_wil_2020;

public class Listitem {


    private String head;
    private String desp;
//    private String imageurl;
    private String more;

    public Listitem(String head, String desp,  String more) {
        this.head = head;
        this.desp = desp;
//        this.imageurl = imageurl;
        this.more = more;
    }

    public Listitem(String s, String s1) {
    }

    public String getHead() {
        return head;
    }

    public String getDesp() {
        return desp;
    }

//    public String getImageurl() {
//        return imageurl;
//    }

    public String getMore() {
        return more;
    }
}
