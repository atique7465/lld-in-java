package org.atique;

import java.io.IOException;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 12:33 PM
 */

public interface Processor {
    void process(String command) throws IOException;
}
