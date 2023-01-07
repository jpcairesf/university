package com.backend.university.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "ROOM_SEQ", sequenceName = "ROOM_SEQ")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ROOM", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_ROOM_NAME", columnNames = {"NAME"})})
public class Room {

    @Id
    @GeneratedValue(generator = "ROOM_SEQ")
    @Column(name = "ROOM_ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LOCATION", nullable = false)
    private String location;

}
