package com.dux.software;

import com.dux.software.model.Auth;
import com.dux.software.model.Team;
import com.dux.software.repository.TeamRepository;
import com.dux.software.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("test")) {
            Auth user = new Auth();
            user.setUsername("test");
            user.setPassword(passwordEncoder.encode("12345"));
            userRepository.save(user);
        }
    }

    @Bean
    public CommandLineRunner initData(TeamRepository teamRepository) {
        return args -> {
            teamRepository.save(new Team(1L, "Real Madrid", "La Liga", "España"));
            teamRepository.save(new Team(2L, "FC Barcelona", "La Liga", "España"));
            teamRepository.save(new Team(3L, "Manchester United", "Premier League", "Inglaterra"));
            teamRepository.save(new Team(4L, "Liverpool FC", "Premier League", "Inglaterra"));
            teamRepository.save(new Team(5L, "Juventus FC", "Serie A", "Italia"));
            teamRepository.save(new Team(6L, "AC Milan", "Serie A", "Italia"));
            teamRepository.save(new Team(7L, "Bayern Munich", "Bundesliga", "Alemania"));
            teamRepository.save(new Team(8L, "Borussia Dortmund", "Bundesliga", "Alemania"));
            teamRepository.save(new Team(9L, "Paris Saint-Germain", "Ligue 1", "Francia"));
            teamRepository.save(new Team(10L, "Olympique de Marseille", "Ligue 1", "Francia"));
            teamRepository.save(new Team(11L, "FC Porto", "Primeira Liga", "Portugal"));
            teamRepository.save(new Team(12L, "Sporting CP", "Primeira Liga", "Portugal"));
            teamRepository.save(new Team(13L, "Ajax Amsterdam", "Eredivisie", "Países Bajos"));
            teamRepository.save(new Team(14L, "Feyenoord", "Eredivisie", "Países Bajos"));
            teamRepository.save(new Team(15L, "Celtic FC", "Scottish Premiership", "Escocia"));
            teamRepository.save(new Team(16L, "Rangers FC", "Scottish Premiership", "Escocia"));
            teamRepository.save(new Team(17L, "Galatasaray SK", "Süper Lig", "Turquía"));
            teamRepository.save(new Team(18L, "Fenerbahçe SK", "Süper Lig", "Turquía"));
            teamRepository.save(new Team(19L, "FC Zenit Saint Petersburg", "Premier League Rusa", "Rusia"));
            teamRepository.save(new Team(20L, "Spartak Moscow", "Premier League Rusa", "Rusia"));
            teamRepository.save(new Team(21L, "SL Benfica", "Primeira Liga", "Portugal"));
            teamRepository.save(new Team(22L, "Besiktas JK", "Süper Lig", "Turquía"));
            teamRepository.save(new Team(23L, "SSC Napoli", "Serie A", "Italia"));
            teamRepository.save(new Team(24L, "Atlético Madrid", "La Liga", "España"));
        };
    }
}