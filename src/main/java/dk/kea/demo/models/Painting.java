package dk.kea.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "paintings")
@Entity
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String artist;

    @Column
    private double price;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private int year;


}
