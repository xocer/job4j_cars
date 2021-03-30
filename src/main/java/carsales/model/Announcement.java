package carsales.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Model model;
    @ManyToOne
    private Photo photo;

    private boolean sold;
}
