package com.fsa.dtos;

import com.fsa.entities.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongDto {
    Double duration;
    String lyrics;
    String product_name;
}
