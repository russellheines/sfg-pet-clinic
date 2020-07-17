package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PetType extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}