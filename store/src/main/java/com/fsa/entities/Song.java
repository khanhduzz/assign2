package com.fsa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "songs")
public class Song{

    @EmbeddedId ProductId id;

    @Column(name = "duration", columnDefinition = "DECIMAL(4,2) CHECK (duration > 0)")
    Double duration;

    @Column(columnDefinition = "TEXT")
    String lyrics;

    @MapsId("id")
    @ManyToOne Product product;
}
