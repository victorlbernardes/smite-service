package br.com.victor.smite.smitestatus.repository;


import br.com.victor.smite.smitestatus.Entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByUsername(String username);
}
