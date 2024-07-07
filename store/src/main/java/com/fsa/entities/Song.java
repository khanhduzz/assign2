package com.fsa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "songs")
@AllArgsConstructor
@NoArgsConstructor
public class Song extends Product implements Serializable {

    @Column(name = "duration", columnDefinition = "DECIMAL(4,2) CHECK (duration > 0)")
    Double duration;

    @Column(columnDefinition = "TEXT")
    String lyrics;

}
