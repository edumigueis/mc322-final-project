package helpers.input;

import java.util.List;

public interface ParsingStrategy {
    List<?> parse(String filePath);
}
