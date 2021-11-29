package com.vapasi.myFirstSpringBoot.repository;

import com.vapasi.myFirstSpringBoot.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findByActorNameIn(List<String> actorNames);

    List<MovieEntity> findByDirectorNameIn(List<String> directorNames);

    List<MovieEntity> findByNameIn(List<String> names);

}
