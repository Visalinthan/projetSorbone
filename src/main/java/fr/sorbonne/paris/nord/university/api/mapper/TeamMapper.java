package fr.sorbonne.paris.nord.university.api.mapper;

import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamDto toDto(TeamEntity teamEntity) {
        Long id = teamEntity.getId();
        String name = teamEntity.getName();
        String slogan = teamEntity.getSlogan();

        return new TeamDto(id,name, slogan);
    }

    public TeamEntity toTeamEntity(TeamDto teamDto) {
        return new TeamEntity(teamDto.getName(), teamDto.getSlogan());
    }
}
