package helper;

import classes.BagOfWords;
import classes.Movie;
import classes.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class Helper{
    public static User parseUserYAML(String yamlPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(yamlPath), User.class);
    }
    public static BagOfWords parseBagOfWordsYAML(String yamlPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(yamlPath), BagOfWords.class);
    }
    public static Movie parseMovieYAML(String yamlPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(yamlPath), Movie.class);
    }
}
