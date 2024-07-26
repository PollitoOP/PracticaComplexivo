package com.ista.ejercicio.service;

import java.util.List;
import java.util.Optional;

import com.ista.ejercicio.entity.Song;

public interface SongService {
    Song createSong(Song song);
    List<Song> getAllSongs();
    Optional<Song> getSongById(Long id);
    Song updateSong(Long id, Song song);
    void deleteSong(Long id);

}
