package fr.sorbonne.paris.nord.university.api.mapper;


import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    @Autowired
    private TeamRepository teamRepository;

    //transformer  Modèle en DTO
    public TeamDto toDto(TeamEntity teamEntity) {
        //cette ligne a ajouter pour que delete de frontend marche
        Long id     = teamEntity.getId();
        String name = teamEntity.getName();
        String slogan =teamEntity.getSlogan();

       /* List<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(toList());*/

        return new TeamDto(id,name, slogan);
    }

    public  TeamDto   TeamIdDTO(Long id) {

          TeamEntity  teamEntity = this.teamRepository.findById(id).get() ;

          TeamDto  teamDto = this.toDto(teamEntity) ;
          return  teamDto;

    }


    //Transformer  DTO  en  Modèle
    public TeamEntity toTeamEntity(TeamDto teamDto) {

        return new TeamEntity(teamDto.getName(), teamDto.getSlogan());
    }


}
