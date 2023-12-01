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

    public TeamService() {
        this.teamRepository= teamRepository;
    }

    public List<TeamEntity> getAllTeam(){
        return this.teamRepository.findAll();
    }

    public Optional<TeamEntity> getTeamById(Long id){
        return this.teamRepository.findById(id);
    }

    public TeamEntity saveTeam(TeamEntity team){
          return this.teamRepository.save(team);
    }

    public void deleteTeam(Long id){
        this.teamRepository.deleteById(id);
    }
}
