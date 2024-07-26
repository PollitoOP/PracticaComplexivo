package com.ista.ejercicio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.ejercicio.entity.Playlist;

public interface IPlaylistDao extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByName(String name);

}
