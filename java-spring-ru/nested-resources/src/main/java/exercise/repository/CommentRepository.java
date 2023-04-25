package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    // a method that allows to get all the comments for a certain post by its id
    public Iterable<Comment> findAllByPostId(Long postId);

    // a method that allows to get one comment by its id and id of the post
    public Comment findCommentByIdAndPostId(Long commentId, Long postId);

    // Spring Data will generate the appropriate SQL queries and execute them against the database,
    // no need to write the implementations of the methods
    // END
}
