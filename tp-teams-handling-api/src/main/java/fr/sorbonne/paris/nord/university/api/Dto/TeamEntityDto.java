package fr.sorbonne.paris.nord.university.api.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamEntityDto implements Serializable  {


    private Long id;
    @Column(nullable = false)
    private String nom;
    private String logan;
}
