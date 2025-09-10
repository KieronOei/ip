package cortana.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CortanaExceptionTest {

    @Test
    void constructorStoresMessage() {
        String msg = "Error occurred";
        CortanaException e = new CortanaException(msg);
        assertEquals(msg, e.getMessage());
    }
}
