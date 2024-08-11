package com.dux.software.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import com.dux.software.dto.TeamDto;
import com.dux.software.exceptions.EquipoNoEncontradoException;
import com.dux.software.model.Team;
import com.dux.software.repository.TeamRepository;
import com.dux.software.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTeams() {
        Team team1 = new Team(1L, "Real Madrid", "La Liga", "España");
        Team team2 = new Team(2L, "FC Barcelona", "La Liga", "España");

        when(teamRepository.findAll()).thenReturn(List.of(team1, team2));

        List<TeamDto> result = teamService.findTeams();

        assertEquals(2, result.size());
        assertEquals("Real Madrid", result.get(0).getNombre());
        assertEquals("FC Barcelona", result.get(1).getNombre());
    }

    @Test
    void testFindTeamById() {
        Team team = new Team(1L, "Real Madrid", "La Liga", "España");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        TeamDto result = teamService.findTeamById(1L);

        assertEquals("Real Madrid", result.getNombre());
    }

    @Test
    void testFindTeamByIdNotFound() {
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EquipoNoEncontradoException.class, () -> {
            teamService.findTeamById(1L);
        });

        assertEquals("Equipo no encontrado", exception.getMessage());
    }

    @Test
    void testFindByName() {
        Team team = new Team(1L, "FC Barcelona", "La Liga", "España");
        Team team2 = new Team(2L, "Liverpool FC", "Premier League", "Inglaterra");
        Team team3 = new Team(2L, "Juventus FC", "Serie A", "Italia");

        when(teamRepository.findByName("FC")).thenReturn(List.of(team, team2, team3));

        List<TeamDto> result = teamService.findByName("FC");

        assertEquals(3, result.size());
        assertEquals("FC Barcelona", result.get(0).getNombre());
        assertEquals("Liverpool FC", result.get(1).getNombre());
        assertEquals("Juventus FC", result.get(2).getNombre());
    }

    @Test
    void testSaveTeam() {
        TeamDto teamDto = TeamDto.builder()
                .nombre("Tottenham")
                .liga("Premier League")
                .pais("Inglaterra")
                .build();
        Team team = new Team(1L, "Tottenham", "Premier League", "Inglaterra");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        TeamDto result = teamService.saveTeam(teamDto);

        assertEquals("Tottenham", result.getNombre());
    }

    @Test
    void testUpdateTeam() {
        TeamDto teamDto = TeamDto.builder()
                .nombre("Tottenham")
                .liga("Premier League")
                .pais("Reino Unido")
                .build();
        Team existingTeam = new Team(1L, "Tottenham", "Premier League", "Inglaterra");
        Team updatedTeam = new Team(1L, "Tottenham", "Premier League", "Reino Unido");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(any(Team.class))).thenReturn(updatedTeam);

        TeamDto result = teamService.updateTeam(1L, teamDto);

        assertEquals("Tottenham", result.getNombre());
        assertEquals("Reino Unido", result.getPais());
    }

    @Test
    void testUpdateTeamNotFound() {
        TeamDto teamDto = TeamDto.builder()
                .nombre("Tottenham")
                .liga("Premier League")
                .pais("Reino Unido")
                .build();

        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EquipoNoEncontradoException.class, () -> {
            teamService.updateTeam(1L, teamDto);
        });

        assertEquals("Equipo no encontrado", exception.getMessage());
    }

    @Test
    void testDeleteTeam() {
        when(teamRepository.existsById(1L)).thenReturn(true);

        teamService.deleteTeam(1L);

        verify(teamRepository).deleteById(1L);
    }

    @Test
    void testDeleteTeamNotFound() {
        when(teamRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(EquipoNoEncontradoException.class, () -> {
            teamService.deleteTeam(1L);
        });

        assertEquals("Equipo no encontrado", exception.getMessage());
    }
}