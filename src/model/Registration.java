package model;

import java.time.LocalDate;

public class Registration {
    private int r_id;
    private int s_id;
    private int c_id;
    private LocalDate date;

    public Registration(int r_id, int s_id, int c_id, LocalDate date) {
        this.r_id = r_id;
        this.s_id = s_id;
        this.c_id = c_id;
        this.date = date;
    }

    public Registration() {
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "r_id=" + r_id + ", s_id=" + s_id + ", c_id=" + c_id + ", date=" + date;
    }
}
