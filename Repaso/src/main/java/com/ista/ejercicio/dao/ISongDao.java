package com.ista.ejercicio.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.ejercicio.entity.Song;

public interface ISongDao extends JpaRepository<Song, Long> {

}
