package com.vgtstptlk.gramrestapi.repository;

import com.vgtstptlk.gramrestapi.domains.Message;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();
    List<Message> findByLoginFromAndLoginTo(String loginFrom, String loginTo);
    List<Message> findAllByLoginTo(String loginTo);
    List<Message> findAllByLoginFrom(String loginFrom);
}
