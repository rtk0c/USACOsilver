package usaco.common.pregen;


public class MethodNotSupportedException extends RuntimeException {

    public static final long serialVersionUID = -1L;

    public MethodNotSupportedException() {
        super();
    }
    public MethodNotSupportedException(String reason) {
        super(reason);
    }

}
