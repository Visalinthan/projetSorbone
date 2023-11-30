package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.controller.TeamController;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamController teamController;

    @MockBean
    private TeamService teamService;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(teamController);
    }

    private static TeamEntity getTeam(){
        TeamEntity teamEntity = new TeamEntity();

        teamEntity.setId(1L);
        teamEntity.setName("OL");
        teamEntity.setSlogan("ALLEZ L'OL");

        return teamEntity;
    }


    @Test
    public void testGetTeamById() throws Exception {
         mockMvc.perform(get("/api/team/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTeams() throws Exception {
        mockMvc.perform(get("/api/allteams")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
