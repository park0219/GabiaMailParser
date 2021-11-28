package me.park.GabiaMailParser.model;

public class Mail {

    private int mail_seq;
    private String user_id;
    private String mail_title;
    private String mail_from;
    private String mail_date;
    private String mail_important;
    private String registration_date;

    public Mail(String user_id, String mail_title, String mail_from, String mail_date, String mail_important) {
        this.user_id = user_id;
        this.mail_title = mail_title;
        this.mail_from = mail_from;
        this.mail_date = mail_date;
        this.mail_important = mail_important;
    }

    public Mail(int mail_seq, String user_id, String mail_title, String mail_from, String mail_date, String mail_important, String registration_date) {
        this.mail_seq = mail_seq;
        this.user_id = user_id;
        this.mail_title = mail_title;
        this.mail_from = mail_from;
        this.mail_date = mail_date;
        this.mail_important = mail_important;
        this.registration_date = registration_date;
    }

    public int getMail_seq() {
        return mail_seq;
    }

    public void setMail_seq(int mail_seq) {
        this.mail_seq = mail_seq;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMail_title() {
        return mail_title;
    }

    public void setMail_title(String mail_title) {
        this.mail_title = mail_title;
    }

    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getMail_date() {
        return mail_date;
    }

    public void setMail_date(String mail_date) {
        this.mail_date = mail_date;
    }

    public String getMail_important() {
        return mail_important;
    }

    public void setMail_important(String mail_important) {
        this.mail_important = mail_important;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
}
