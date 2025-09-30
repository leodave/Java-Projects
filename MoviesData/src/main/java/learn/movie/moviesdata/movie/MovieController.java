package learn.movie.moviesdata.movie;

import java.util.List;

import learn.movie.moviesdata.movie.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getAllMovies")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<ResponseDto> addMovie(@RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Movie added successfully"));

    }

    @GetMapping("/getMovie")
    public ResponseEntity<MovieDto> getMovie(@RequestParam String title) {
        MovieDto movieDto = movieService.getMovie(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieDto);
    }

    @PutMapping("editMovie")
    public ResponseEntity<MovieDto> editMovie(@RequestParam Long id, @RequestBody MovieDto movieDto) {
        MovieDto dto = movieService.editMovie(id, movieDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
}
