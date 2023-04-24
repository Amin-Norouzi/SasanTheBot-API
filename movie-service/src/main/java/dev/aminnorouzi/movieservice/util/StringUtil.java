package dev.aminnorouzi.movieservice.util;

import dev.aminnorouzi.movieservice.exception.ImdbIdConversionException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringUtil {

    private static final UrlValidator URL_VALIDATOR = UrlValidator.getInstance();
    private static final Pattern IMDB_ID_PATTERN = Pattern.compile("(tt\\d+)");
    private static final String SIMPLE_IMDB_URL = "imdb.com";

    @Value("${movie.client.api.imdb.base-url}")
    private String imdbBaseUrl;

    public boolean isUrl(String str) {
        return URL_VALIDATOR.isValid(str);
    }

    public String format(String str, Object... args) {
        return String.format(str, args);
    }

    public boolean containsImdbId(String query) {
        if (isUrl(query) && query.contains(SIMPLE_IMDB_URL)) {
            return true;
        }

        return query.matches(IMDB_ID_PATTERN.pattern());
    }

    public String generateImdbUrl(String imdbId) {
        return imdbBaseUrl.concat(imdbId);
    }

    public String extractImdbId(String url) {
        Matcher matcher = IMDB_ID_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }

        throw new ImdbIdConversionException("Failed to extract imdb id!");
    }
}
