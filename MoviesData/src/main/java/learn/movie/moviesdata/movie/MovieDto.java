package learn.movie.moviesdata.movie;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private Integer year;

    private Double rating;

    private List<String> genres;

}
