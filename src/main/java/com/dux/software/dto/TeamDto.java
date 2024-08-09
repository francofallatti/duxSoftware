package com.dux.software.dto;

import com.dux.software.model.Team;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamDto {

    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La liga es obligatoria")
    private String liga;
    @NotBlank(message = "El país es obligatorio")
    private String pais;

    public Team toEntity() {
        return Team.builder()
                .id(this.id)
                .nombre(this.nombre)
                .liga(this.liga)
                .pais(this.pais)
                .build();
    }

    // Método para convertir de Team a TeamDto
    public static TeamDto fromTeamToTeamDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .nombre(team.getNombre())
                .liga(team.getLiga())
                .pais(team.getPais())
                .build();
    }
}
