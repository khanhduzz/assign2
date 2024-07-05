package com.fsa.mappers;

import com.fsa.dtos.SongDto;
import com.fsa.entities.Product;
import com.fsa.entities.Song;
import com.fsa.repositories.ProductRepository;

public class SongMapper {

    public static Song toSong (SongDto songDto) {
        Song song = new Song();
        Product product = ProductRepository.findByName(songDto.getProduct_name());
        song.setDuration(songDto.getDuration());
        song.setLyrics(songDto.getLyrics());
        song.setProduct(product);
        return song;
    }
}
