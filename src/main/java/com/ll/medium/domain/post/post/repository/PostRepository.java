package com.ll.medium.domain.post.post.repository;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom  {
    List<Post> findTop30ByIsPublishedOrderByIdDesc(boolean isPublished);

}
