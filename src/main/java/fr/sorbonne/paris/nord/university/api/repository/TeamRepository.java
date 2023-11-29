package fr.sorbonne.paris.nord.university.api.repository;



import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
