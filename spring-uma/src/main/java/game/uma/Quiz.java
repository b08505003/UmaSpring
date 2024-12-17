package game.uma;

import game.uma.entity.Uma;

public class Quiz {
    private int number;
    private Uma uma;
    private int point;

    public Quiz(){

    }

    public Quiz(int number, Uma uma) {
        this.number = number;
        this.uma = uma;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Uma getUma() {
        return uma;
    }

    public void setUma(Uma uma) {
        this.uma = uma;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
