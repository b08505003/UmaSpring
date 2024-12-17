package game.uma.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "umas")
public class Uma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "This is required. Please enter a name.")
    @Size(max = 20, message = "The name is too long")
    @Column(name = "name_jp")
    private String nameJP;

    @Size(max = 20, message = "The name is too long")
    @Column(name = "name_ch")
    private String nameCH;

    @Size(max = 20, message = "The name is too long")
    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "adapt")
    private String adapt;

    @OneToMany(mappedBy = "uma", fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Race> races;

    public Uma(){

    }

    public Uma(String nameJP, String nameCH, String nameEN, String adapt) {
        this.nameJP = nameJP;
        this.nameCH = nameCH;
        this.nameEN = nameEN;
        this.adapt = adapt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameJP() {
        return nameJP;
    }

    public void setNameJP(String nameJP) {
        this.nameJP = nameJP;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getAdapt() {
        return adapt;
    }

    public void setAdapt(String adapt) {
        this.adapt = adapt;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public void addRace(Race race){
        if(races == null){
            races = new ArrayList<>();
        }
        races.add(race);
    }

    @Override
    public String toString() {
        return "Uma{" +
                "id=" + id +
                ", nameJP='" + nameJP + '\'' +
                ", nameCH='" + nameCH + '\'' +
                ", nameEN='" + nameEN + '\'' +
                ", adapt='" + adapt + '\'' +
                '}';
    }
}
