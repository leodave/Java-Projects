package learn.movie.moviesdata.movie;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto toDto(MovieEntity movieEntity);

    MovieEntity toEntity(MovieDto movieDto);

    List<MovieDto> toDto(List<MovieEntity> movieEntity);

    List<MovieEntity> toEntity(List<MovieDto> movieDto);

}
