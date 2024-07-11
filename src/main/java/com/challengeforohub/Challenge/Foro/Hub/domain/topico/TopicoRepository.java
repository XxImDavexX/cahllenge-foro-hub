package com.challengeforohub.Challenge.Foro.Hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {


}
