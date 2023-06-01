package com.nekochan.redchan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seiyu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seiyu {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "seiyu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Character> characters;

}
