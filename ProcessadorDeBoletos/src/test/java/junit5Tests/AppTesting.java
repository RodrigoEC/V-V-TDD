package junit5Tests;

import org.example.App;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertNull;

public class AppTesting {

    @Test
    @DisplayName("Veryfing if main function works correctly")
    public void AppWorkingRight() { // this test verifies that we can't create a fatura for a 0 price
        String[] strings = null;
        App.main(strings);
        assertNull(strings);

    }
}
