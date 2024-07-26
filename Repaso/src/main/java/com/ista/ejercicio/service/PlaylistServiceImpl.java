package com.ista.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.ejercicio.dao.IPlaylistDao;
import com.ista.ejercicio.entity.Playlist;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private IPlaylistDao playlistDao;

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistDao.save(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistDao.findAll();
    }

    @Override
    public Optional<Playlist> getPlaylistByName(String name) {
        return playlistDao.findByName(name);
    }

    @Override
    public Playlist updatePlaylist(String name, Playlist playlist) {
        Optional<Playlist> existingPlaylist = playlistDao.findByName(name);
        if (existingPlaylist.isPresent()) {
            Playlist updatedPlaylist = existingPlaylist.get();
            updatedPlaylist.setDescription(playlist.getDescription());
            updatedPlaylist.setSongs(playlist.getSongs());
            return playlistDao.save(updatedPlaylist);
        }
        return null;
    }

    @Override
    public void deletePlaylist(String name) {
        Optional<Playlist> playlist = playlistDao.findByName(name);
        playlist.ifPresent(playlistDao::delete);
    }
}
