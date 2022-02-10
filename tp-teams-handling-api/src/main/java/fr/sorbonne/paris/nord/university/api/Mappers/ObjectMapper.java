package fr.sorbonne.paris.nord.university.api.Mappers;

import fr.sorbonne.paris.nord.university.api.Dto.TeamEntityDto;
import fr.sorbonne.paris.nord.university.api.Entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {
    public TeamEntity toTeamEntity(TeamEntityDto dto) {
        return   TeamEntity.builder()
                .id(dto.getId())
                .name(dto.getNom())
                .slogan(dto.getLogan())
                .build();

    }
    public TeamEntityDto toTeamEntityDto(TeamEntity teamEntity) {
        return   TeamEntityDto.builder()
                .id(teamEntity.getId())
                .nom(teamEntity.getName())
                .logan(teamEntity.getSlogan())
                .build();

    }
}
