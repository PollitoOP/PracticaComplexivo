package com.ista.ejercicio.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ista.ejercicio.entity.Playlist;
import com.ista.ejercicio.entity.Song;
import com.ista.ejercicio.service.PlaylistService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/crear")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist createdPlaylist = playlistService.createPlaylist(playlist);
        return ResponseEntity.status(201).body(createdPlaylist);
    }

    @GetMapping("/lists")
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{listName}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable String listName) {
        return playlistService.getPlaylistByName(listName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @PutMapping("/{listName}")
    public ResponseEntity<Void> updatePlaylist(@PathVariable String listName, @RequestBody Playlist playlist) {
        Playlist updatedPlaylist = playlistService.updatePlaylist(listName, playlist);
        if (updatedPlaylist != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String listName) {
        playlistService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{listName}/songs")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable String listName, @RequestBody Song song) {
        Optional<Playlist> playlistOpt = playlistService.getPlaylistByName(listName);
        if (playlistOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Set<Song> songs = playlist.getSongs();
            songs.add(song);
            playlist.setSongs(songs);
            playlistService.updatePlaylist(listName, playlist);
            return ResponseEntity.ok(playlist);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{listName}/songs/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable String listName, @PathVariable Long songId) {
        Optional<Playlist> playlistOpt = playlistService.getPlaylistByName(listName);
        if (playlistOpt.isPresent()) {
            Playlist playlist = playlistOpt.get();
            Set<Song> songs = playlist.getSongs();
            songs.removeIf(song -> song.getId().equals(songId));
            playlist.setSongs(songs);
            playlistService.updatePlaylist(listName, playlist);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}