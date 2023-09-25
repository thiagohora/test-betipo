package es.betipo.test.keysmanager.entities;

import es.betipo.test.keysmanager.model.EntityStatus;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Serdeable
@MappedEntity("services")
@Data
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 20, min = 20)
    private String nif;

    @NotBlank
    @Size(max = 20)
    private String status;

    @NotBlank
    @Size(max = 20)
    private String type;

    @NotNull
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
