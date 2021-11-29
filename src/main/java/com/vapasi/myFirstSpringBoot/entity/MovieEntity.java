package com.vapasi.myFirstSpringBoot.entity;

import com.vapasi.myFirstSpringBoot.dto.MovieDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String actorName;
    private String directorName;

    public MovieEntity(Integer id, String name, String actorName, String directorName) {
        this.id = id;
        this.name = name;
        this.actorName = actorName;
        this.directorName = directorName;
    }

    public MovieEntity() {
    }

    public static MovieEntity entityFrom(MovieDto movieDto) {
       return new MovieEntity(null, movieDto.getName(), movieDto.getActorName(), movieDto.getDirectorName());
    }

    public MovieDto movieDto() {
        return new MovieDto(this.id, this.name, this.actorName, this.directorName);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actorName='" + actorName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity movies = (MovieEntity) o;
        return Objects.equals(name, movies.name) && Objects.equals(actorName, movies.actorName) && Objects.equals(directorName, movies.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, actorName, directorName);
    }
}
