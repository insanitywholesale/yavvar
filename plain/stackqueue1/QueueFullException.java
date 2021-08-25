package stackqueue1;

public class QueueFullException extends RuntimeException {

    public QueueFullException(String s) {
        super(s);
    }
}
