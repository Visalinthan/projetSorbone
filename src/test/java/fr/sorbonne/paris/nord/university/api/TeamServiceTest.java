package fr.sorbonne.paris.nord.university.api;

//import org.junit.jupiter.api.Test;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService  teamService;

    public TeamServiceTest() {
        this.teamService = teamService ;
    }

    @Test
    public void shouldReturnTheExpectedTeam_whenGetTeamByExistingId() {

        // Given.
        Long teamId = 1L;

        // When.
        Optional<TeamEntity> team = teamService.getEquipeById(teamId);

        // Then.
        assertThat(team).isPresent();
        // Add more assertions as needed.


    }

    @Test
    public void givenExistingId_whenGetTeamById_thenExpectedTeamInResult() {
        // Given.
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(3L);
        teamEntity.setName("Sample Team");
        // Set other properties as needed.

        // Save the teamEntity to the database or mock the behavior of the repository.

        teamService.saveTeam(teamEntity);

        // When.
        Long teamIdToRetrieve = 3L;
        Optional<TeamEntity> retrievedTeam = teamService.getEquipeById(teamIdToRetrieve);

        // Then.
        assertThat(retrievedTeam).isPresent();
        assertThat(retrievedTeam.get().getId()).isEqualTo(3L);
        assertThat(retrievedTeam.get().getName()).isEqualTo("Sample Team");


    }

    @Test
    public void given(){
        // Given.
        // When. String result = ...
        // Then. assertThat(result).isNotNull().isNotEmpty()
        // Given.
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(1L);
        teamEntity.setName("Sample Team");
        // Set other properties as needed.

        // Save the teamEntity to the database or mock the behavior of the repository.
        teamService.saveTeam(teamEntity);

        // When.
        Long teamIdToRetrieve = 1L;
        Optional<TeamEntity> retrievedTeam = teamService.getEquipeById(teamIdToRetrieve);

        // Then.
        assertThat(retrievedTeam).isNotNull().isPresent();
        assertThat(retrievedTeam.get().getId()).isEqualTo(1L);
        assertThat(retrievedTeam.get().getName()).isEqualTo("Sample Team");



        }

}






