package fr.sorbonne.paris.nord.university.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import fr.sorbonne.paris.nord.university.api.controller.TeamController;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
//import io.restassured.mapper.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



//@SpringBootTest
@WebMvcTest
public class TeamControllerTest {

    @MockBean
    private TeamController teamController;

    @MockBean
    private TeamService teamService;

    @Autowired
    private MockMvc mockMvc;


    //est utilisée pour initialiser
    // l'environnement de test Rest Assured avec Mockito.
    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        MockitoAnnotations.openMocks(this);
        RestAssuredMockMvc.standaloneSetup(teamController);

    }

    /*@Test
    public void testGetWebService() {
        // Mocking TeamService
        TeamEntity tm = new TeamEntity();
        tm.setId(3L);
        tm.setName("OL");
        when(teamService.getEquipeById(anyLong())).thenReturn(Optional.of(tm));


        // Performing the RestAssuredMockMvc test
        given()
                .when()
                .get("/WebServiceTeams/teams/{id}" , 3L)
                .then()
                .statusCode(200)
                .body("name", equalTo("OL"));

        // Verifying that TeamService method was called
        verify(teamService, times(1)).getEquipeById(anyLong());
    }*/

        @Test
        public void testGetTeamById() throws Exception {


            mockMvc.perform(get("/WebServiceTeams/teams/{id}", 1)
                            .accept(MediaType.APPLICATION_JSON))

                    .andExpect(status().isOk());
                    //.andExpect(content().json("{\"name\":\"PSG\",\"slogan\":\"Revons plus grand\"}"))
                    //.andExpect(jsonPath("name").value("PSG"));
            //System.out.println(mockMvc.perform(get("/WebServiceTeams/teams/{id}")));
            //.andExpect(jsonPath("$.slogan").value("john@example.com"));
        }

    private static TeamEntity getEquipe(){

        TeamEntity teamEntity = new TeamEntity();

         teamEntity.setId(8L);
         teamEntity.setName("PSG New");
         teamEntity.setSlogan("ok le meuilleur");

        return teamEntity;
    }


    @Test
    void addEquipe() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("name", TextNode.valueOf("Test"));
        node.set("slogan", TextNode.valueOf("ga ga"));


        String jsonNotes = node.toString();

        TeamEntity note = getEquipe();

        when(teamService.saveTeam((TeamEntity) any())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/teams/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNotes))
                .andExpect(status().isOk());

    }

    @Test
    void addEquipeTestException() throws Exception {



        TeamEntity note = getEquipe();

        when(teamService.saveTeam((TeamEntity) any())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/teams/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());

    }



    @Test
    void testDelete()   throws Exception{
        /*TeamEntity teamEntity = new TeamEntity();
        TeamService serviceMock = Mockito.mock(TeamService.class);
        //teamEntity.setServiceDependency(serviceMock);

        // Configurer le mock pour la méthode delete avec un argument donné
        //Mockito.when(serviceMock.deleteEquipe(Long.valueOf(Mockito.anyString()))).thenReturn(true);
        Mockito.when(serviceMock.deleteEquipe(Mockito.anyLong())).thenReturn(true);

        // Appeler la méthode à tester
        boolean result = myClass.delete("someId");

        // Effectuer des assertions sur le résultat
        Assertions.assertTrue(result);

        // Vérifier que la méthode delete du mock a été appelée avec le bon argument
        Mockito.verify(serviceMock).delete("someId");*/
        //TeamEntity notes = getEquipe();

        doNothing().when(teamService).deleteEquipe(Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teams/delete/{id}",1)
        ).andExpect(status().isOk());

    }


        /*

 import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.mediscreen.consultation.model.Notes;
import com.mediscreen.consultation.service.ConsultationService;
import com.mediscreen.consultation.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ConsultationControllerTest {

    private MockMvc mockMvc;

    @Mock
    ConsultationService consultationService;

    @Mock
    ReportService reportService;

    @InjectMocks
    ConsultationController consultationController;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(consultationController)
                .build();
    }

    private static Notes getNote(){

        Notes notes = new Notes();

        notes.setId("100");
        notes.setTitle("Test");
        notes.setDate("2022-05-05");
        notes.setContent("Note de test");
        notes.setPatientId("1");

        return notes;
    }


    @Test
    void listOfAllNoteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/listNote")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getNotesByIdTest() throws Exception {
        Notes notes = getNote();

        when(consultationService.findById(anyString())).thenReturn(java.util.Optional.of(notes));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/getNote/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listNotesByPatientTest() throws Exception {

        List<Notes> notes = new ArrayList<>();

        notes.add(getNote());

        when(consultationService.listNotesByPatientId(anyLong())).thenReturn(notes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/notesByPatient/100")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addNoteTest() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("title", TextNode.valueOf("Test"));
        node.set("date", TextNode.valueOf("2022-05-05"));
        node.set("content", TextNode.valueOf("Note de test"));
        node.set("patientId", TextNode.valueOf("1"));

        String jsonNotes = node.toString();

        Notes note = getNote();

        when(consultationService.save((Notes) any(),anyLong())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/consultation/addNote/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNotes))
                .andExpect(status().isCreated());
    }

    @Test
    void updateNotesTest() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        ObjectNode node = obj.createObjectNode();
        node.set("title", TextNode.valueOf("Test"));
        node.set("date", TextNode.valueOf("2022-05-05"));
        node.set("content", TextNode.valueOf("Note de test"));
        node.set("patientId", TextNode.valueOf("1"));

        String jsonNotes = node.toString();

        Notes note = getNote();

        when(consultationService.update((Notes) any(),anyString())).thenReturn(note);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/consultation/updateNotes/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNotes))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotesTest() throws Exception {
        Notes notes = getNote();

        doNothing().when(consultationService).deleteById(anyString());

        mockMvc.perform(MockMvcRequestBuilders.delete("/consultation/deleteNotes/{id}",1)
        ).andExpect(status().isOk());
    }

    @Test
    void reportPatientTest() throws Exception {
        Notes notes = getNote();

        when(reportService.getReportPatient(any())).thenReturn("1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consultation/reportPatient/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
*/




    } //Fin de classe




