package br.com.victor.smite.security;

import br.com.victor.smite.repository.PlayerRepository;
import br.com.victor.smite.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PlayerRepository playerRepository;

    public UserDetailsServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername -> Username: " + username);
        Player player = playerRepository.findByUsername(username);
        if (player == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(player.getUsername(), player.getPassword(), emptyList());
    }
}
