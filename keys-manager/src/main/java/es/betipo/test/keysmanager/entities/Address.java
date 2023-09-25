package es.betipo.test.keysmanager.entities;


import io.micronaut.data.annotation.Embeddable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@Serdeable
public class Address {

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String floor;

    @NotBlank
    private String door;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String city;

    @NotBlank
    private String province;

    @NotBlank
    private String country;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

}