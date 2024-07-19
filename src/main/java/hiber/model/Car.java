package hiber.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne(mappedBy = "car")
    private User user;

    @Column
    private int series;

    @Column
    private String model;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {

    }

}
