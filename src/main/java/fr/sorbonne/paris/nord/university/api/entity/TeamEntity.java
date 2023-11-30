package fr.sorbonne.paris.nord.university.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class TeamEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String slogan;

    public TeamEntity() {
    }

    public TeamEntity(String name, String slogan) {
        this.name = name;
        this.slogan = slogan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
