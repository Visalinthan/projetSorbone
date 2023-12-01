package fr.sorbonne.paris.nord.university.api.controller;


import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class TeamController {

	@Autowired
	private TeamService teamService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@Autowired
	private TeamMapper teamMapper;

	@GetMapping("/teams")
	 public ResponseEntity<List<TeamDto>> getAllTeams() {
		List<TeamDto> teamDto = this.teamService.getAllTeam()
				.stream()
				.map(teamMapper::toDto)
				.collect(toList());

		return new ResponseEntity<>(teamDto, HttpStatus.OK);
	 }

	 @GetMapping("/teams/{id}")
	public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id){
		TeamDto teamDto = this.teamService.getTeamById(id)
				.stream()
				.map(teamMapper::toDto)
				.findFirst().get();

		 return new ResponseEntity<>(teamDto, HttpStatus.OK);
	 }

	@PostMapping("/teams/add")
	 public ResponseEntity<TeamDto> newTeam(@RequestBody TeamDto teamDto){
		 TeamEntity teamEntity = teamMapper.toTeamEntity(teamDto);
		 this.teamService.saveTeam(teamEntity);
		 return new ResponseEntity<TeamDto>(teamDto, HttpStatus.CREATED);
	 }

	 @DeleteMapping("/teams/delete/{id}")
	 public ResponseEntity<String> deleteTeam(@PathVariable Long id){
		this.teamService.deleteTeam(id);

		String response = "L'équipe avec id "+id+ " a été supprimé";

		return new ResponseEntity<>(response, HttpStatus.OK);
	 }


}
