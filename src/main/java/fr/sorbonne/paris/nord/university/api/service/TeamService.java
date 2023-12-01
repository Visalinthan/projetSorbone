package fr.sorbonne.paris.nord.university.api.service;


import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.exception.TeamInvalidException;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService  {

    @Autowired
    private TeamRepository teamRepository;

    public TeamService() {
        this.teamRepository= teamRepository;
    }

    public List<TeamEntity> getAllTeam(){

        if(this.teamRepository.findAll().isEmpty() ){
            throw new TeamInvalidException("Liste d'équipe vide");
        }else{
            return this.teamRepository.findAll() ;
        }
    }

    public Optional<TeamEntity> getTeamById(Long id){

        if (teamRepository.findById(id).isPresent()) {
            return this.teamRepository.findById(id);
        } else {
            throw new TeamInvalidException("Équipe introuvable avec l'ID : " + id);
        }
    }

    public TeamEntity saveTeam(TeamEntity team){

        if (team.getName().isEmpty() || team.getSlogan().isEmpty()) {
            throw new TeamInvalidException("Le champ 'slogan' ou 'name' ne peut pas être null ou vide.");
        }else{
            return this.teamRepository.save(team);
        }

    }

    public void deleteTeam(Long id){

        if (teamRepository.findById(id).isPresent()) {
             this.teamRepository.deleteById(id);
        } else {
            throw new TeamInvalidException("Équipe introuvable avec l'ID : " + id);
        }
    }
}
