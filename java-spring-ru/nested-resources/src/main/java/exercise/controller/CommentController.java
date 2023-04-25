package exercise.controller;

import java.util.Optional; // added
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments") // вывод всех комментариев для конкретного поста
    public Iterable<Comment> getAllComments(@PathVariable("postId") Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}") // вывод конкретного комментария для поста
    public Comment getComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        Comment comment = commentRepository.findCommentByIdAndPostId(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        return comment;
    }

    @PostMapping("/{postId}/comments") // создание нового комментария для поста
    public void createComment(@PathVariable("postId") Long postId, @RequestBody Comment newComment) {
        Optional<Post> post = postRepository.findById(postId);
        newComment.setPost(post.get());
        commentRepository.save(newComment);
    }

    @PatchMapping("/{postId}/comments/{commentId}") // редактирование конкретного комментария поста
    public void updateComment(@PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId,
                              @RequestBody Comment updatedComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (updatedComment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }

        updatedComment.setId(commentId);
        updatedComment.setPost(comment.get().getPost());
        commentRepository.save(updatedComment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}") // удаление конкретного комментария поста
    public void deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        Comment commentToDelete = commentRepository.findCommentByIdAndPostId(commentId, postId);
        if (commentToDelete == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }
    // END
}
