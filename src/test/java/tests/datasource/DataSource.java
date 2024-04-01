package tests.datasource;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * Interface to provide data for tests.
 */
public interface DataSource {
    
    /**
     * Get data for tests.
     * 
     * @return Stream of arguments.
     */
    Stream<Arguments> get();

}
