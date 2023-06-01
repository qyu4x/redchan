package com.nekochan.redchan.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterRequest {

    private String name;

    private String anime;

    private String gender;

    private Integer age;

}
