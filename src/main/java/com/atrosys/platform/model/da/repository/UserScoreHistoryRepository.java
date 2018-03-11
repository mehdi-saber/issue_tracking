package com.atrosys.platform.model.da.repository;

import com.atrosys.platform.model.to.UserScoreHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kasra on 1/28/2018.
 */
public interface UserScoreHistoryRepository extends JpaRepository<UserScoreHistory,Long> {
}
