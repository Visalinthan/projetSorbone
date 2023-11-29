package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	public TeamController ()  {
		this.teamService = teamService;
	}

	@GetMapping("/hello")
	 public String getTeams() {
	 return "Hello World";
	 }



	//Récuperer la liste des équipes
	@GetMapping("/ListEquipes")
	public List<TeamEntity> getAllTeams() {
		return  this.teamService.getAllEquipes();
	}

	//Récuperer une équipe à partir de son ID
	@GetMapping("/teams/{id}")
	public Optional<TeamEntity> getTeamByid(@PathVariable Long id) {
		return   this.teamService.getEquipeById(id);
	}

	//Crée une équipe
	@PostMapping("/teams")
	public  void  saveTeam(@RequestBody  TeamEntity teamEntity)  {
		       this.teamService.saveTeam(teamEntity);
	}

	 //Supprimer une équipe existante
	@DeleteMapping ("/teams/{id}")
	public  void    deleteTeams(@PathVariable Long id) {
		    this.teamService.deleteEquipe(id);
	}


}
