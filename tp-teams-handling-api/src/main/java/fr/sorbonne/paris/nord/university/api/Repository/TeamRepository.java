package fr.sorbonne.paris.nord.university.api.Repository;


import fr.sorbonne.paris.nord.university.api.Dto.TeamEntityDto;
import fr.sorbonne.paris.nord.university.api.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity,Long> {


}
