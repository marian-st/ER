package State.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Monitoring implements Entry{
    @Id
    @GeneratedValue
    @Column(name = "monitoring_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "recovery_id")
    private Recovery recovery;

    //TODO add all
}
