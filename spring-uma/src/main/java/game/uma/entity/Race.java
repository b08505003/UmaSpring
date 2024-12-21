package game.uma.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "year")
    @NotNull(message = "This is required.")
    private Integer year;

    @Column(name = "jockey")
    @NotNull(message = "This is required.")
    private String jockey;

    @Column(name = "place")
    @NotNull(message = "This is required.")
    private String place;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "uma_id")
    private Uma uma;

    @ManyToOne
    @JoinColumn(name = "title")
    private RaceInfo raceInfo;

    public Race(){

    }

    public Race(int year, String jockey, String place) {
        this.year = year;
        this.jockey = jockey;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getJockey() {
        return jockey;
    }

    public void setJockey(String jockey) {
        this.jockey = jockey;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Uma getUma() {
        return uma;
    }

    public void setUma(Uma uma) {
        this.uma = uma;
    }

    public RaceInfo getRaceInfo() {
        return raceInfo;
    }

    public void setRaceInfo(RaceInfo raceInfo) {
        this.raceInfo = raceInfo;
    }
}
