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
@CrossOrigin (origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamMapper mapper;

	public TeamController ()  {
		this.teamService = teamService;
	}

	/*@GetMapping("/hello")
	 public String getTeams() {
	 return "Hello World";
	 }*/



	//Récuperer la liste des équipes
	@GetMapping("/teams")
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
	//@PostMapping("/addteams")
	@RequestMapping(value ="/teams/add", method = RequestMethod.POST)
	public  ResponseEntity<TeamEntity>  saveTeam(@RequestBody  TeamDto teamDto)  {

		if(teamDto.getName().isEmpty() || teamDto.getSlogan().isEmpty()) {
			throw new TeamInvalidException("Le champ 'slogan' ou 'name' ne peut pas etre null ou vide");

		}  else {

		TeamEntity teamEntity1 = mapper.toTeamEntity(teamDto);

		 this.teamService.saveTeam(teamEntity1);

		return  new ResponseEntity<>(teamEntity1, HttpStatus.CREATED);}

	}

	 //Supprimer une équipe existante
	@DeleteMapping ("/teams/delete/{id}")
	public  ResponseEntity<String>    deleteTeams(@PathVariable Long id) {

		if(teamService.getEquipeById(id).isPresent()) {

			String response = "Equipe avec id:  " + id + "a été supprimé ";

			this.teamService.deleteEquipe(id);
            //CEtte solution à faire pour le Retour sur FrontEnd
			//return new ResponseEntity<>(response, HttpStatus.OK);
			return ResponseEntity.noContent().build();
		}  else  {
			throw new TeamInvalidException("L'équipe introuvable avec l'ID :" +id);
		}


	}


    //Pour envoyer messages  d'exception
	@ExceptionHandler(TeamInvalidException.class)
    public ResponseEntity<String> handleTeamInvalideException(TeamInvalidException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

}//Fin classe Controleur
