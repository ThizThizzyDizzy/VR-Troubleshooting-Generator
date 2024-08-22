package com.thizthizzydizzy.vr_troubleshooting;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Locale;
public class Page{
    private final String name;
    private String title;
    private ArrayList<String> paragraphs = new ArrayList<>();
    private ArrayList<String> suffixParagraphs = new ArrayList<>();
    private ArrayList<Link> links = new ArrayList<>();
    private ArrayList<Page> pages = new ArrayList<>();
    private ArrayList<Page> problems = new ArrayList<>();
    private Page parent;
    private int actions;
    private int resources;
    public Page(String name, String title){
        this(name);
        this.title = title;
    }
    public Page(String name){
        this.name = name;
    }
    public void generate(File folder) throws IOException{
        if(title==null&&parent!=null)title = parent.title;
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
            +"        <h1 id=\"head\">VR Troubleshooting (WIP)</h1>\n"
            +"        <h2 id='title'>"+title+"</h2>\n"
            +"        <div>\n"
            +(paragraphs.isEmpty()?"":"            <p>"+String.join("</p>\n            <p>", paragraphs)+"</p>\n")
            +(links.isEmpty()?"":"            <p>"+String.join("</p>\n            <p>", genLinks(links))+"</p>\n")
            +(suffixParagraphs.isEmpty()?"":"            <p>"+String.join("</p>\n            <p>", suffixParagraphs)+"</p>\n")
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
        return paragraph(text, false);
    }
    public Page paragraph(String text, boolean suffix){
        if(suffix)suffixParagraphs.add(text);
        else
            paragraphs.add(text);
        return this;
    }
    public Page seeAlso(String linkText, String linkDestination){
        String prefix = "";
        if(linkText.toLowerCase(Locale.ROOT).startsWith("the ")){
            prefix += linkText.substring(0, 4);
            linkText = linkText.substring(4);
        }
        resources++;
        return paragraph("For more troubleshooting support, see "+prefix+"<a href=\""+linkDestination+"\">"+linkText+"</a>", true);
    }
    public Page action(String title, String text){
        actions++;
        paragraphs.add("<strong>"+title+"</strong>");
        paragraphs.add(text);
        return this;
    }
    public Page subpage(String title, String caption, Page page){
        page.parent = this;
        links.add(new Link("./"+page.name+"/", title, caption, page));
        pages.add(page);
        return this;
    }
    private ArrayList<String> genLinks(ArrayList<Link> links){
        ArrayList<String> strs = new ArrayList<>();
        for(Link link : links)strs.add(link.toString());
        return strs;
    }
    public Page problem(String name, String caption, Page page){
        if(problems.isEmpty()){
            paragraph("What issue are you having?");
        }
        problems.add(page);
        return subpage(name, caption, page);
    }
    public int countProblems(){
        int total = problems.size();
        for(var page : pages)total += page.countProblems();
        return total;
    }
    public int countActions(){
        int total = actions;
        for(var page : pages)total += page.countActions();
        return total;
    }
    public int countResources(){
        int total = resources;
        for(var page : pages)total += page.countResources();
        return total;
    }
    public boolean isInIssue(){
        return parent!=null&&(parent.problems.contains(this)||parent.isInIssue());
    }
}
