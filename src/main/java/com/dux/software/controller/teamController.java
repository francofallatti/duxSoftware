package com.dux.software.controller;

import com.dux.software.dto.ErrorDto;
import com.dux.software.dto.TeamDto;
import com.dux.software.exceptions.EquipoNoEncontradoException;
import com.dux.software.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equipos")
public class teamController {

    @Autowired
    private TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<TeamDto>> getTeams(){
        return new ResponseEntity<>(teamService.findTeams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        TeamDto teamDto = teamService.findTeamById(id);

        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TeamDto>> findTeamByName(@RequestParam("nombre") String name) {
        return new ResponseEntity<>(teamService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TeamDto> postTeam(@Valid @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.saveTeam(teamDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> putTeamById(@PathVariable("id") Long id, @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.updateTeam(id, teamDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable("id") Long id){
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
