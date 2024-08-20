package com.thizthizzydizzy.vr_troubleshooting;
public class Link{
    private final String link;
    private final String title;
    private final String caption;
    public Link(String link, String title, String caption){
        this.link = link;
        this.title = title;
        this.caption = caption;
    }
    @Override
    public String toString(){
        return "<a href=\""+link+"\">"+title+"</a>"+(caption!=null?" "+caption:"");
    }
}
