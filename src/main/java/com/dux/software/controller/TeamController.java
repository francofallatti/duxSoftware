package com.dux.software.controller;

import com.dux.software.dto.TeamDto;
import com.dux.software.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Team Controller")
@RestController
@RequestMapping("/equipos")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Lista de todos los equipos de fútbol registrados")
    @GetMapping()
    public ResponseEntity<List<TeamDto>> getTeams(){
        return new ResponseEntity<>(teamService.findTeams(), HttpStatus.OK);
    }

    @Operation(summary = "Información del equipo correspondiente al ID proporcionado")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        TeamDto teamDto = teamService.findTeamById(id);

        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @Operation(summary = "Lista de equipos cuyos nombres contienen el valor proporcionado")
    @GetMapping("/buscar")
    public ResponseEntity<List<TeamDto>> findTeamByName(@RequestParam("nombre") String name) {
        return new ResponseEntity<>(teamService.findByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Crea un nuevo equipo")
    @PostMapping()
    public ResponseEntity<TeamDto> postTeam(@Valid @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.saveTeam(teamDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un equipo según el ID proporcionado")
    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> putTeamById(@PathVariable("id") Long id, @Valid @RequestBody TeamDto teamDto){
        return new ResponseEntity<>(teamService.updateTeam(id, teamDto), HttpStatus.OK);
    }

    @Operation(summary = "Elimina el equipo correspondiente al ID proporcionado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable("id") Long id){
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
