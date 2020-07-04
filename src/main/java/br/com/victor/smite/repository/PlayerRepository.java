package br.com.victor.smite.repository;


import br.com.victor.smite.Entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByUsername(String username);
}
