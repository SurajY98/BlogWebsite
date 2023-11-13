/**
 * 
 */
package com.suraj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog.entity.Comment;

/**
 * @author suraj.yadav
 * @created on Nov 7, 2023
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
