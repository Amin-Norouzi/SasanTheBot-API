package dev.aminnorouzi.movieservice.util;

import dev.aminnorouzi.movieservice.dto.MovieRequest;
import dev.aminnorouzi.movieservice.model.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil {

    public void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    public boolean hasDifference(Movie movie, MovieRequest request) {
        if (!request.getTmdbId().equals(movie.getTmdbId())) return true;
        if (!request.getImdbId().equals(movie.getImdbId())) return true;
        if (!request.getTitle().equals(movie.getTitle())) return true;
        if (!request.getOverview().equals(movie.getOverview())) return true;
        if (!request.getPoster().equals(movie.getPoster())) return true;
        if (!request.getBackdrop().equals(movie.getBackdrop())) return true;
        if (!request.getRuntime().equals(movie.getRuntime())) return true;
        if (!request.getWebsite().equals(movie.getWebsite())) return true;
        if (!request.getType().equals(movie.getType())) return true;
        if (!request.getRating().equals(movie.getRating())) return true;
        if (!request.getEpisodes().equals(movie.getEpisodes())) return true;
        if (!request.getSeasons().equals(movie.getSeasons())) return true;
        return !request.getReleased().equals(movie.getReleased());
    }
}
