package cortana.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CortanaExceptionTest {

    @Test
    void constructorStoresMessage() {
        String msg = "Error occurred";
        CortanaException e = new CortanaException(msg);
        assertEquals(msg, e.getMessage());
    }
}
