package com.thizthizzydizzy.vr_troubleshooting;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
public class Page{
    private final String name;
    private final String title;
    private ArrayList<String> paragraphs = new ArrayList<>();
    private ArrayList<Link> links = new ArrayList<>();
    private ArrayList<Page> pages = new ArrayList<>();
    public Page(String name, String title){
        this.name = name;
        this.title = title;
    }
    public void generate(File folder) throws IOException{
        File file = new File(folder, "index.html");
        if(!folder.exists())folder.mkdirs();
        int depth = file.getAbsolutePath().split("[\\/\\\\]").length-Main.root.getAbsolutePath().split("[\\/\\\\]").length;
        String ups = "";
        for(int i = 0; i<depth; i++)ups += "../";
        Files.writeString(file.toPath(),
            "<!DOCTYPE html>\n"
            +"<html>\n"
            +"    <head>\n"
            +"        <title>"+title+"</title>\n"
            +"        <meta charset=\"UTF-8\">\n"
            +"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            +"        <link rel=\"stylesheet\" href=\""+ups+"blog/style.css\">\n"
            +"    </head>\n"
            +"    <body>\n"
            +"        <a class=\"button-link\" href=\"../\">\n"
            +"            <div class=\"button\">\n"
            +"                Back\n"
            +"            </div>\n"
            +"        </a>\n"
            +"        <h1 id=\"head\">VR Troubleshooting</h1>\n"
            +"        <h2 id='title'>"+title+"</h2>\n"
            +"        <div>\n"
            +(paragraphs.isEmpty()?"":"            <p>"+String.join("</p>\n            <p>", paragraphs))+"</p>\n"
            +(links.isEmpty()?"":"            <p>"+String.join("</p>\n            <p>", genLinks(links)))+"</p>\n"
            +"        </div>\n"
            +"        <footer>\n"
            +"            <div class='links'>\n"
            +"                <div>\n"
            +"                    <div>Support me on</div>\n"
            +"                    <div class='links'>\n"
            +"                        <p><a href=https://www.patreon.com/thizthizzydizzy>Patreon</a></p>\n"
            +"                        <p><a href=https://ko-fi.com/thizthizzydizzy>Ko-Fi</a></p>\n"
            +"                    </div>\n"
            +"                </div>\n"
            +"                <div>\n"
            +"                    <p>Thank you for visiting.</p>\n"
            +"                    <p>Have a great day!</p>\n"
            +"                </div>\n"
            +"                <div>\n"
            +"                    <div>Find me on</div>\n"
            +"                    <div class='links'>\n"
            +"                        <p><a href=https://discord.gg/dhcPSMt>Discord</a></p>\n"
            +"                    </div>\n"
            +"                </div>\n"
            +"            </div>\n"
            +"            <br>\n"
            +"        </footer>\n"
            +"        <script src='blog.js'></script>\n"
            +"    </body>\n"
            +"</html>\n");
        for(var page : pages){
            page.generate(new File(folder, page.name));
        }
    }
    public Page paragraph(String text){
        paragraphs.add(text);
        return this;
    }
    public Page subpage(String title, String caption, Page page){
        links.add(new Link("./"+page.name+"/", title, caption));
        pages.add(page);
        return this;
    }
    private ArrayList<String> genLinks(ArrayList<Link> links){
        ArrayList<String> strs = new ArrayList<>();
        for(Link link : links)strs.add(link.toString());
        return strs;
    }
}
