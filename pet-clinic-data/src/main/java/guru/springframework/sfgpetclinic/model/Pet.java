package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pet extends BaseEntity {

    private String name;

    @ManyToOne
    //@JoinColumn(name="type_id")
    private PetType petType;

    @ManyToOne
    //@JoinColumn(name="owner_id")
    private Owner owner;

    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
}