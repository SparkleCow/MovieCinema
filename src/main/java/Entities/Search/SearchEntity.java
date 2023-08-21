package Entities.Search;

public class SearchEntity{
    private String movieName;

    public SearchEntity(){}
    public SearchEntity(String movieName){
        this.movieName = movieName;
    }

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }
}
