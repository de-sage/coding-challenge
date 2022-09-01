package com.dev.codingchallenge.userportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class ApplicationUser extends RepresentationModel<ApplicationUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique=true)
    private String email;

    private LocalDate dateOfBirth;

    @Transient
    private long age;

    public long getAge() {
        LocalDate today = LocalDate.now();
//        LocalDate birthday = LocalDate.of(1987, 09, 24);
        Period period = Period.between(dateOfBirth, today);
        return period.getYears();
    }
}
