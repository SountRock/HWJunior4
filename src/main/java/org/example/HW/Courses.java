package org.example.HW;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "наименование")
    private String title;
    @Column(name = "длительность")
    private int duration;

    public Courses(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Courses() {}

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int  duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
