package fr.sorbonne.paris.nord.university.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.sorbonne.paris.nord.university.api.dto.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exception.TeamInvalidException;
import fr.sorbonne.paris.nord.university.api.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/teams")
	public List<TeamDto> getTeams() {
		List<TeamEntity> teams = teamService.getAllTeam();
		return teams.stream().map(teamEntity -> modelMapper.map(teamEntity, TeamDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/teams/{id}")
	public ResponseEntity<?> getTeamById(@PathVariable Long id) {
		Optional<TeamEntity> team = teamService.getTeamById(id);

		return team.map(teamEntity -> {
			TeamDto teamDto = modelMapper.map(teamEntity, TeamDto.class);
			return ResponseEntity.ok().body(teamDto);
		}).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}

	@PostMapping("/teams")
	public ResponseEntity<String> newTeam(@RequestBody TeamDto teamDto) {
		if (teamDto.getName().isEmpty() || teamDto.getSlogan().isEmpty()) {
			throw new TeamInvalidException("Le champ 'slogan' ou 'name' ne peut pas être null ou vide.");
		} else {
			TeamEntity teamEntity = modelMapper.map(teamDto, TeamEntity.class);

			teamService.saveTeam(teamEntity);
			return ResponseEntity.status(HttpStatus.CREATED).body("Team " + teamDto.getName() + " a été créée");
		}
	}

	@DeleteMapping("/teams/{id}")
	public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
		if (teamService.getTeamById(id).isPresent()) {
			teamService.deleteTeam(id);
			return ResponseEntity.ok("Team a été supprimé avec succès");
		} else {
			throw new TeamInvalidException("Équipe introuvable avec l'ID : " + id);
		}
	}

	@ExceptionHandler(TeamInvalidException.class)
	public ResponseEntity<String> handleTeamInvalidException(TeamInvalidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
