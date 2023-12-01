package fr.sorbonne.paris.nord.university.api.controller;


import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exception.TeamInvalidException;
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

		if (teamDto.isEmpty()) {
			throw new TeamInvalidException("Liste d'équipe vide");
		}else{
			return new ResponseEntity<>(teamDto, HttpStatus.OK);
		}

	 }

	 @GetMapping("/teams/{id}")
	public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id){

		 if (teamService.getTeamById(id).isPresent()) {
			 TeamDto teamDto = this.teamService.getTeamById(id)
					 .stream()
					 .map(teamMapper::toDto)
					 .findFirst().get();

			 return new ResponseEntity<>(teamDto, HttpStatus.OK);
		 } else {
			 throw new TeamInvalidException("Équipe introuvable avec l'ID : " + id);
		 }
	 }

	@PostMapping("/teams/add")
	 public ResponseEntity<TeamDto> newTeam(@RequestBody TeamDto teamDto){

		if (teamDto.getName().isEmpty() || teamDto.getSlogan().isEmpty()) {
			throw new TeamInvalidException("Le champ 'slogan' ou 'name' ne peut pas être null ou vide.");
		}else{
			TeamEntity teamEntity = teamMapper.toTeamEntity(teamDto);
			this.teamService.saveTeam(teamEntity);
			return new ResponseEntity<TeamDto>(teamDto, HttpStatus.CREATED);
		}

	 }

	 @DeleteMapping("/teams/delete/{id}")
	 public ResponseEntity<String> deleteTeam(@PathVariable Long id){

		 if (teamService.getTeamById(id).isPresent()) {

			 this.teamService.deleteTeam(id);
			 String response = "L'équipe avec id "+id+ " a été supprimé";

			 return new ResponseEntity<>(response, HttpStatus.OK);
		 } else {
			 throw new TeamInvalidException("Équipe introuvable avec l'ID : " + id);
		 }
	 }

	@ExceptionHandler(TeamInvalidException.class)
	public ResponseEntity<String> handleTeamInvalidException(TeamInvalidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}


}
