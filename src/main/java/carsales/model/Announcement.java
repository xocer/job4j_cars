package carsales.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Brand brand;
    @ManyToOne(cascade = CascadeType.ALL)
    private Model model;
    @ManyToOne(cascade = CascadeType.ALL)
    private Photo photo;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    private boolean sold;
}
