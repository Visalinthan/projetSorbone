package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exception.TeamInvalidException;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService  {


    @Autowired
    private TeamRepository teamRepository;

    public TeamService(TeamRepository  teamRepository) {

        this.teamRepository = teamRepository;
    }


    //   Récuperer toutes les équipes en base de données o
    public List<TeamEntity> getAllEquipes ()  {
       if (teamRepository != null) {
           return this.teamRepository.findAll();
       } else {
           throw new TeamInvalidException("Introuvable car la liste d'équipe est vide :");
       }
    }
    // Récuperer une équipe en base à partir de son ID
    public Optional<TeamEntity> getEquipeById (Long id) {
        if (id != null) {
            return this.teamRepository.findById(id);
        }  else {
            throw new TeamInvalidException("ID introuvable :" +id);
        }
    }

    //Insérer une équipe en base
    public  TeamEntity  saveTeam (TeamEntity teamEntity)  {
         if (teamEntity != null) {
             return this.teamRepository.save(teamEntity);
         }   else  {
             throw new TeamInvalidException("Création échoué :");
         }
    }

    //Supprimer une équipe existante de la base de données à partir de son ID
    public  void  deleteEquipe(Long id) {

       if(id != null) {
           this.teamRepository.deleteById(id);
       }  else   {
           throw new TeamInvalidException("Suppression échoué :");
       }
    }


    @ExceptionHandler(TeamInvalidException.class)
    public ResponseEntity<String> handleTeamInvalideException(TeamInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }



}//Fin classe Service
