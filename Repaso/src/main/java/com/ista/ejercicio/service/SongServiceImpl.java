package com.ista.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.ejercicio.dao.ISongDao;
import com.ista.ejercicio.entity.Song;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private ISongDao songDao;

    @Override
    public Song createSong(Song song) {
        return songDao.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songDao.findAll();
    }

    @Override
    public Optional<Song> getSongById(Long id) {
        return songDao.findById(id);
    }

    @Override
    public Song updateSong(Long id, Song song) {
        Optional<Song> existingSong = songDao.findById(id);
        if (existingSong.isPresent()) {
            Song updatedSong = existingSong.get();
            updatedSong.setTitle(song.getTitle());
            updatedSong.setArtist(song.getArtist());
            updatedSong.setAlbum(song.getAlbum());
            updatedSong.setYear(song.getYear());
            return songDao.save(updatedSong);
        }
        return null;
    }

    @Override
    public void deleteSong(Long id) {
    	songDao.deleteById(id);
    }
}
