package com.lifesup.log.repository;

import com.lifesup.log.model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer> {

    List<LogEntity> findAllByOrderByCreatedAtDesc();
    List<LogEntity> findAllByOrderByUpdatedAtDesc();
    List<LogEntity> findAllByOrderByModel();
    @Query("SELECT l FROM LogEntity l ORDER BY l.total_cost DESC")
    List<LogEntity> findAllByOrderByTotalCostDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.total_cost ASC")
    List<LogEntity> findAllByOrderByTotalCostAsc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.promt_tokens DESC")
    List<LogEntity> findAllByOrderByPromtTokensDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.promt_tokens ASC")
    List<LogEntity> findAllByOrderByPromtTokensAsc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.promt_cost DESC")
    List<LogEntity> findAllByOrderByPromtCostDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.promt_cost ASC")
    List<LogEntity> findAllByOrderByPromtCostAsc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.completion_tokens DESC")
    List<LogEntity> findAllByOrderByCompletionTokensDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.completion_tokens ASC")
    List<LogEntity> findAllByOrderByCompletionTokensAsc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.completion_cost DESC")
    List<LogEntity> findAllByOrderByCompletionCostDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.completion_cost ASC")
    List<LogEntity> findAllByOrderByCompletionCostAsc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.total_tokens DESC")
    List<LogEntity> findAllByOrderByTotalTokensDesc();
    @Query("SELECT l FROM LogEntity l ORDER BY l.total_tokens ASC")
    List<LogEntity> findAllByOrderByTotalTokensAsc();
    //search all fields
    @Query("SELECT l FROM LogEntity l WHERE " +
            "LOWER(l.model) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.full_message) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.promt) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.user_message) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.reply) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LogEntity> searchAllFields(String keyword);
}
