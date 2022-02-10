package fr.sorbonne.paris.nord.university.api.Service;


import fr.sorbonne.paris.nord.university.api.Dto.TeamEntityDto;
import fr.sorbonne.paris.nord.university.api.Entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.Mappers.ObjectMapper;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ObjectMapper mapper;

    //Insérer une équipe en base
    public TeamEntityDto save(TeamEntityDto teamEntityDto) {
        TeamEntity teamEntityToSave = mapper.toTeamEntity(teamEntityDto);
        this.teamRepository.save(teamEntityToSave);
        return mapper.toTeamEntityDto(teamEntityToSave);
    }

    //Récuperer toutes les équipes en base de données
    public List<TeamEntityDto> findAll() {
        return this.teamRepository.findAll()
                .stream()
                .map(mapper::toTeamEntityDto)
                .toList();

    }

    //Récuperer une équipe en base à partir de son ID
    public Optional<TeamEntityDto> findTeamEntityById(Long EntityId) {
        if (EntityId == null) {
            return null;
        }
        Optional<TeamEntity> teamEntity = this.teamRepository.findById(EntityId);
        return Optional.ofNullable(teamRepository.findById(EntityId)
                .map(mapper::toTeamEntityDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun teamEntity avec l'ID = " + EntityId)));
    }

    //Modifier une équipe en base

    public TeamEntityDto UpdateTeam(TeamEntityDto teamEntityParameter) {
        this.teamRepository.findById(teamEntityParameter.getId())
                .orElseThrow(EntityNotFoundException::new);



        return mapper.toTeamEntityDto(this.teamRepository.save(mapper.toTeamEntity(teamEntityParameter)));


    }

    //Supprimer une équipe existante de la base de données à partir de son ID
    public void DeleteTeam(Long EntityId) {
 /*       TeamEntity teamEntity = this.teamRepository.findById(EntityId)
                .orElseThrow(EntityNotFoundException::new);*/
        if (EntityId == null) {
            log.error("Team ID is null");
            return;
        }
        this.teamRepository.deleteById(EntityId);
    }
}
