
package connection;

import java.io.IOException;

public interface ISimpleHttpClient {
    
    public String get(String url) throws IOException;
}
