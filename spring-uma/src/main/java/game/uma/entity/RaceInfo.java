package game.uma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "race_info")
public class RaceInfo {
    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "distance")
    private String distance;

    public RaceInfo(){

    }

    public RaceInfo(String title, String distance) {
        this.title = title;
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "RaceInfo{" +
                "title='" + title + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
