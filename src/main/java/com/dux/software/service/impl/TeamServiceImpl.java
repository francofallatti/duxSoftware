package com.dux.software.service.impl;

import com.dux.software.dto.TeamDto;
import com.dux.software.exceptions.EquipoNoEncontradoException;
import com.dux.software.model.Team;
import com.dux.software.repository.TeamRepository;
import com.dux.software.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<TeamDto> findTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream()
                .map(TeamDto::fromTeamToTeamDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto findTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new EquipoNoEncontradoException("Equipo no encontrado"));
        return TeamDto.fromTeamToTeamDto(team);
    }

    @Override
    public List<TeamDto> findByName(String name) {
        List<Team> teams = teamRepository.findByName(name);

        return teams.stream()
                .map(TeamDto::fromTeamToTeamDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto) {
        Team savedTeam = teamRepository.save(teamDto.toEntity());
        return TeamDto.fromTeamToTeamDto(savedTeam);
    }

    @Override
    public TeamDto updateTeam(Long id, TeamDto teamDto) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow( () ->
                        new EquipoNoEncontradoException("Equipo no encontrado")
                );

        existingTeam.setNombre(teamDto.getNombre());
        existingTeam.setLiga(teamDto.getLiga());
        existingTeam.setPais(teamDto.getPais());

        Team updatedTeam = teamRepository.save(existingTeam);

        return TeamDto.fromTeamToTeamDto(updatedTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new EquipoNoEncontradoException("Equipo no encontrado");
        }

        teamRepository.deleteById(id);
    }

}
