package com.harulab.adapfit.domain.counseling.domain.repository;

import com.harulab.adapfit.domain.counseling.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
