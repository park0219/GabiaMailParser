package me.park.GabiaMailParser.model;

public class Template {

    private String user_id;
    private String template_content;
    private String last_modify;

    public Template() {
        this.user_id = "";
        this.template_content = "";
        this.last_modify = "";
    }

    public Template(String user_id, String template_content, String last_modify) {
        this.user_id = user_id;
        this.template_content = template_content;
        this.last_modify = last_modify;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTemplate_content() {
        return template_content;
    }

    public void setTemplate_content(String template_content) {
        this.template_content = template_content;
    }

    public String getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(String last_modify) {
        this.last_modify = last_modify;
    }
}
