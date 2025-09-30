package learn.movie.moviesdata.movie;

import java.util.List;
import java.util.Optional;

import learn.movie.moviesdata.movie.Exception.MovieExistsException;
import learn.movie.moviesdata.movie.Exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public List<MovieDto> getAllMovies() {
        return movieMapper.toDto(movieRepository.findAll());
    }

    public void addMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieMapper.toEntity(movieDto);
        Optional<MovieEntity> movie = movieRepository.findByTitle(movieDto.getTitle());
        if (movie.isPresent()) {
            throw new MovieExistsException("Movie with title " + movieDto.getTitle() + " already exists");
        }
        movieRepository.save(movieEntity);
    }

    public MovieDto getMovie(String title) {
        MovieEntity movieEntity = movieRepository.findByTitle(title).orElseThrow(
                () -> new MovieNotFoundException(title));
        return movieMapper.toDto(movieEntity);
    }

    public MovieDto editMovie(Long id, MovieDto movieDto) {
        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id.toString())
        );
        if (movieDto.getTitle() != null)
            movieEntity.setTitle(movieDto.getTitle());
        if (movieDto.getYear() != null)
            movieEntity.setYear(movieDto.getYear());
        if (movieDto.getRating() != null)
            movieEntity.setRating(movieDto.getRating());
        if (movieDto.getGenres() != null)
            movieEntity.setGenres(movieDto.getGenres());
        MovieDto dto = movieMapper.toDto(movieRepository.save(movieEntity));
        return dto;
    }
}
