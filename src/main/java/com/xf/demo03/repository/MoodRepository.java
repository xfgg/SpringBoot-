package com.xf.demo03.repository;

import com.xf.demo03.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xfgg
 */
public interface MoodRepository extends JpaRepository<Mood,String> {
}
