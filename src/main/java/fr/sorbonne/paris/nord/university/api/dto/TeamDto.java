package fr.sorbonne.paris.nord.university.api.dto;

public class TeamDto {



    private  String  name ;
    private  String slogan ;


    public TeamDto()  {}
    public TeamDto(String name, String slogan) {
        this.name= name;
        this.slogan= slogan;
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
