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

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/teams")
	 public List<TeamEntity> getTeams() {
		return this.teamService.getAllTeam();
	 }

	 @GetMapping("/teams/{id}")
	public Optional<TeamEntity> getTeamById(@PathVariable Long id){
		return this.teamService.getTeamById(id);
	 }

	 @PostMapping("/teams")
	 public void newTeam(@RequestBody TeamEntity teamEntity){
		 this.teamService.saveTeam(teamEntity);
	 }

	 @DeleteMapping("/teams/{id}")
	 public void deleteTeam(@PathVariable Long id){
		this.teamService.deleteTeam(id);
	 }

}
