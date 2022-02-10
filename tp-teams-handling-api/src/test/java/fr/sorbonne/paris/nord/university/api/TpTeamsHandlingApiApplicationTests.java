package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.Dto.TeamEntityDto;
import fr.sorbonne.paris.nord.university.api.Entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.Mappers.ObjectMapper;
import fr.sorbonne.paris.nord.university.api.Repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TpTeamsHandlingApiApplicationTests {
	@Mock
	private TeamRepository teamRepository;
	@Mock
	private ObjectMapper teamFactory;

	@InjectMocks
	private TeamService service;

	@Test
	void getAllTeams(){
		//given
		final ArrayList<TeamEntity> teamEntities = new ArrayList<TeamEntity>();
		teamEntities.add(new TeamEntity());
		when(teamRepository.findAll()).thenReturn(teamEntities);
		doCallRealMethod().when(teamFactory).toTeamEntityDto(any());
		//when
		final List<TeamEntityDto> allTeams=service.findAll();
		//then
		assertEquals(1,allTeams.size());
	}

	@Test
	public void getTeamById(){
		//GIVEN
		final TeamEntity entity = new TeamEntity();
		entity.setId(1L);
		entity.setName("NAME");
		entity.setSlogan("SLOGAN");

		when(teamRepository.findById(anyLong())).thenReturn(Optional.of(entity));
		doCallRealMethod().when(teamFactory).toTeamEntityDto(any());
		//WHEN
		Optional<TeamEntityDto>dto=service.findTeamEntityById(1L);
		//THEN
		assertEquals(true,dto.isPresent());
		assertEquals("NAME",dto.get().getNom());
		assertEquals(1L,dto.get().getId());
		assertEquals("SLOGAN",dto.get().getLogan());

	}
	@Test
	public void addTeam(){
		//GIVEN
		final TeamEntityDto TeamDTO =new TeamEntityDto();
		TeamEntity entity=new TeamEntity();
		when(teamFactory.toTeamEntity(any())).thenReturn(entity);
		when(teamRepository.save(any(TeamEntity.class))).thenAnswer(i->i.getArgument(0));
		//WHEN
		service.save(TeamDTO);
		//THEN
		verify(teamFactory).toTeamEntity(TeamDTO);
		verify(teamRepository).save(entity);
	}
	@Test
	public void updateTeam(){
		//GIVEN
		final TeamEntityDto teamDTO=new TeamEntityDto();
		teamDTO.setId(1L);
		teamDTO.setNom("Name");
		teamDTO.setLogan("Slogan");

		final TeamEntity entity = new TeamEntity();
		entity.setName("XXX");
		when(teamRepository.findById(anyLong())).thenReturn(Optional.of(entity));
		//WHEN
		service.UpdateTeam(teamDTO);
		//THEN
		assertEquals("NAME",entity.getName());

	}

	@Test
	public void deleteTeam(){
		//GIVEN
		doNothing().when(teamRepository).deleteById(anyLong());
		//WHEN
		service.DeleteTeam(1L);
		//THEN
		verify(teamRepository,times(1)).deleteById(1L);
	}

}
