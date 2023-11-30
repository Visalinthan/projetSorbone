package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/WebServiceTeams")
public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamMapper mapper;

	public TeamController ()  {
		this.teamService = teamService;
	}

	@GetMapping("/hello")
	 public String getTeams() {
	 return "Hello World";
	 }



	//Récuperer la liste des équipes
	@GetMapping("/ListEquipes")

	public ResponseEntity<List<TeamDto>> getAllTeams() {

		//User user = userService.getUser(id);
		List<TeamDto> teamDto = teamService.getAllEquipes()
				.stream()
				.map(mapper::toDto)
				.collect(toList());
        if(teamDto != null) {
			return ResponseEntity.ok(teamDto);
		} else {
			return  ResponseEntity.notFound().build();
		}
	}
	/*public List<TeamDto> getAllTeams() {
		return  this.teamService.getAllEquipes()
				.stream()
				.map(mapper::toDto)
				.collect(toList());
	}*/



	//Récuperer une équipe à partir de son ID
	@GetMapping("/teams/{id}")
	public ResponseEntity<TeamDto> getTeamByid(@PathVariable Long id) {

		/*return   this.teamService.getEquipeById(id).
				stream()
				.map(mapper::toDto)
				.findFirst();*/
		TeamDto teamDto = teamService.getEquipeById(id)
				.stream()
				.map(mapper::toDto)
				.findFirst().get() ;
		//if(teamDto != null) {
		/*TeamDto teamDtoOrDefault = teamDto.orElse(new TeamDto
				());*/
		if(teamDto !=null) {
			return ResponseEntity.ok(teamDto);
		} else {
			return  ResponseEntity.notFound().build();
		}

	}

	//Crée une équipe
	@PostMapping("/teams")
	public  TeamDto  saveTeam(@RequestBody  TeamDto teamDto)  {

		TeamEntity teamEntity1 = mapper.toTeamEntity(teamDto);

		 this.teamService.saveTeam(teamEntity1);

         /*return  this.teamService.getEquipeById(teamEntity1.getId()).
				 stream()
				 .map(mapper::toDto)
				 .findFirst().get();*/

		return mapper.TeamIdDTO(teamEntity1.getId());
	}

	 //Supprimer une équipe existante
	@DeleteMapping ("/teams/{id}")
	public  String    deleteTeams(@PathVariable Long id) {
		    this.teamService.deleteEquipe(id);
			return "L'equipe avec id = " +id +  "a été supprimé " ;
	}





}
