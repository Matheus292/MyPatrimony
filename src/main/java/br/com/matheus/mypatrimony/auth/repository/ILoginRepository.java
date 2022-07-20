package br.com.matheus.mypatrimony.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginRepository extends JpaRepository<Login, Long> {

    public Optional<Login> findByLogin(String login);

}
