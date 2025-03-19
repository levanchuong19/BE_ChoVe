package com.example.ChoVe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UUID id;

    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name;

    @NotBlank(message = "IATA Code cannot be blank!")
    @Size(min = 2, max = 2, message = "IATA Code must be exactly 2 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "IATA Code must be uppercase letters only")
    String iAtaCode;

    @NotBlank(message = "ICAO Code cannot be blank!")
    @Size(min = 3, max = 3, message = "ICAO Code must be exactly 3 characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "ICAO Code must be uppercase letters only")
    String iCaoCode;

    @NotBlank(message = "Location cannot be blank!")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    String country;

    @NotBlank(message = "Callsign cannot be blank!")
    @Size(max = 255, message = "Callsign must not exceed 255 characters")
    String callsign;

    @NotBlank(message = "Founded cannot be blank!")
    @Size(max = 4, message = "Founded must not exceed 255 characters")
    String founded;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    boolean isDeleted = false;


//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
 //   @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true) //  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY
    //1 hãng bay có thể có nhiều máy bay
    // cascade = CascadeType.ALL: Khi xóa hãng bay, máy bay cũng bị xóa.
    //orphanRemoval = true: Nếu máy bay không còn thuộc hãng bay, nó sẽ bị xóa.
//    @JsonIgnore
//    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<Aircraft> aircrafts = new HashSet<>();

    //    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany
    @JoinColumn(name = "airline_id", referencedColumnName = "id", insertable = false, updatable = false)
    Set<Aircraft> aircrafts = new HashSet<>();

}
