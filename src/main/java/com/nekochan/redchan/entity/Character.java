package com.nekochan.redchan.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "character")
public class Character {

    @Id
    private String id;

    private String name;

    private String anime;

    private String gender;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "seiyu_id")
    private Seiyu seiyu;

}
