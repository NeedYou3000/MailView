package com.example.mailview;

public class EmailModel {
    private String user;
    private String title;
    private String hour;
    private String content;
    private boolean liked;

    public EmailModel(String user, String hour, String title, String content, boolean liked) {
        this.user = user;
        this.title = title;
        this.hour = hour;
        this.content = content;
        this.liked = liked;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
