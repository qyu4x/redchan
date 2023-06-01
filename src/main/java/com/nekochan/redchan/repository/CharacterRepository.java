package com.nekochan.redchan.repository;

import com.nekochan.redchan.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {
}
