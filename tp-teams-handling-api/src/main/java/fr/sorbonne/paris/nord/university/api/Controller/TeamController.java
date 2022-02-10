package fr.sorbonne.paris.nord.university.api.Controller;

import fr.sorbonne.paris.nord.university.api.Dto.TeamEntityDto;
import fr.sorbonne.paris.nord.university.api.Mappers.ObjectMapper;
import fr.sorbonne.paris.nord.university.api.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/TeamController")
//http://localhost:4200/
@CrossOrigin(origins = "*")
public class TeamController {
    private final TeamService teamService;
    private final ObjectMapper mapper;

    //enregistrer une TeamEntityDto
    @PostMapping("/")
    public ResponseEntity<TeamEntityDto> save(@RequestBody TeamEntityDto teamEntityDto) {
        return ResponseEntity.ok(teamService.save(teamEntityDto));
    }

    //Récupérer tous les TeamEntityDto
    // @CrossOrigin(origins ="**")
    @GetMapping("/findAll")
    public ResponseEntity<List<TeamEntityDto>> findAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    //récupérer une TeamEntityDtoById
    @GetMapping("/find/{entityTeam-id}")
    public ResponseEntity<TeamEntityDto> findTeamEntityById(@PathVariable(name = "entityTeam-id") Long entityTeamId) {
        return ResponseEntity.ok(teamService.findTeamEntityById(entityTeamId).get());
    }

    //Modifier une équipe en base
    @PutMapping("/update")
    public ResponseEntity<TeamEntityDto> updateTeamEntity(@RequestBody TeamEntityDto teamEntityDto) {
        return ResponseEntity.ok(teamService.UpdateTeam(teamEntityDto));
    }

    //Supprimer une équipe existante de la base de données à partir de son ID
    @DeleteMapping("/delete/{entityTeam-id}")
    public ResponseEntity<HttpStatus> DeleteTeam(@PathVariable(name = "entityTeam-id") Long entityTeamId) {

        teamService.DeleteTeam(entityTeamId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);

    }

}
