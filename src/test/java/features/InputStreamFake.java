package features;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class InputStreamFake extends InputStream {
    private final List<ByteArrayInputStream> inputStreams = new ArrayList<>();
    private int currentStream = 0;

    public void write(String input) {
        inputStreams.add(new ByteArrayInputStream(input.getBytes()));
    }

    @Override
    public int read() {
        if (inputStreams.isEmpty()) {
            throw new RuntimeException("Nothing has been written to stream.");
        }
        if (currentStream >= inputStreams.size()) {
            return -1;
        }
        var stream = inputStreams.get(currentStream);
        var result = stream.read();
        if (result == -1) {
            currentStream++;
            return this.read();
        }
        return result;
    }
}
