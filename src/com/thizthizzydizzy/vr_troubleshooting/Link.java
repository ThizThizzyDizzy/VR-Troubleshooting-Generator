package com.thizthizzydizzy.vr_troubleshooting;
public class Link{
    private final String link;
    private final String title;
    private final String caption;
    private final Page page;
    public Link(String link, String title, String caption, Page page){
        this.link = link;
        this.title = title;
        this.caption = caption;
        this.page = page;
    }
    @Override
    public String toString(){
        int problems = page.countProblems();
        int actions = page.countActions();
        int resources = page.countResources();
        boolean isInIssue = page.isInIssue();
        String issuesStr = !isInIssue||problems>0?problems+" known issue"+(problems==1?"":"s")+", ":"";
        String resourcesStr = resources>0?", "+resources+" external resource"+(resources==1?"":"s"):"";
        return "<a href=\""+link+"\">"+title+"</a>"+(caption!=null?" "+caption:"")+"<br><span style='font-size: 0.5em'>"+issuesStr+actions+" potential solution"+(actions==1?"":"s")+resourcesStr+"</span>";
    }
    public boolean isValid(){
        int problems = page.countProblems();
        int actions = page.countActions();
        int resources = page.countResources();
        boolean isInIssue = page.isInIssue();
        return isInIssue||problems+actions+resources>0;
    }
}
