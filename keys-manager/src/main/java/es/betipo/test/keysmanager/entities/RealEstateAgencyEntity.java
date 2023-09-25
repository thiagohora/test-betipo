package es.betipo.test.keysmanager.entities;

import es.betipo.test.keysmanager.model.EntityStatus;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@MappedEntity("real_estate_agencies")
@Data
@NoArgsConstructor
public class RealEstateAgencyEntity {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @NotBlank
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String description;
    @NotBlank
    @Size(max = 50)
    private String reference;
    @NotBlank
    @Size(max = 20)
    private String nif;
    @NotBlank
    @Size(max = 20)
    private String status;
    @NotBlank
    @Relation(value = Relation.Kind.EMBEDDED)
    private Address address;

    // Setters and getters

    public void setStatus(EntityStatus status) {
        this.status = status.toString();
    }

    public EntityStatus getStatus() {
        return EntityStatus.fromValue(this.status);
    }

}
