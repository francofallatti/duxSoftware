package com.dux.software.service;

import com.dux.software.dto.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {

    public List<TeamDto> findTeams();
    public TeamDto findTeamById(Long id);
    public List<TeamDto> findByName(String name);
    public TeamDto saveTeam(TeamDto teamDto);
    public TeamDto updateTeam(Long id, TeamDto teamDto);
    public void deleteTeam(Long id);

}
