package com.fiap.fiapjogos.repository;

import com.fiap.fiapjogos.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
