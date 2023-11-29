package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return  this.teamRepository.findAll();
    }
    // Récuperer une équipe en base à partir de son ID
    public Optional<TeamEntity> getEquipeById (Long id) {
        return this.teamRepository.findById(id);
    }

    //Insérer une équipe en base
    public  void  saveTeam (TeamEntity teamEntity)  {
        this.teamRepository.save(teamEntity);
    }

    //Supprimer une équipe existante de la base de données à partir de son ID
    public  void  deleteEquipe(Long id) {
        this.teamRepository.deleteById(id);
    }

}
