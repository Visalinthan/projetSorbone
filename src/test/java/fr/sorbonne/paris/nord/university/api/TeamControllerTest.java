package fr.sorbonne.paris.nord.university.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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
        TeamEntity team= new TeamEntity();
        team.setName("OL");
        team.setSlogan("ALLEZ L'OL");

        return team;
    }


    @Test
    public void testGetTeamById() throws Exception {
         mockMvc.perform(get("/api/teams/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTeams() throws Exception {
        mockMvc.perform(get("/api/teams")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void addTeamTest() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("name", TextNode.valueOf("OL"));
        node.set("slogan", TextNode.valueOf("ALLEZ L'OL"));

        String jsonTeam = node.toString();

        TeamEntity team = getTeam();

        when(teamService.saveTeam((TeamEntity) any())).thenReturn(team);

        mockMvc.perform(
                        post("/api/teams/add")
                        .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                        .content(jsonTeam))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTeamTest() throws Exception {

        doNothing().when(teamService).deleteTeam(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teams/delete/{id}",1)
        ).andExpect(status().isOk());
    }

    @Test
    void addTeamTestException() throws Exception {

        TeamEntity team = getTeam();

        when(teamService.saveTeam((TeamEntity) any())).thenReturn(team);

        mockMvc.perform(
                        post("/api/teams/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(""))
                .andExpect(status().isBadRequest());
    }
}
