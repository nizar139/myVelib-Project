package test.VelibCLUI;

import core.System.VelibSystem;
import core.VelibCLUI.HandleSetupCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HandleSetupTest {

    @Test
    void testHandleSetupWithOneArgument() {
        String[] args = { "oussama" };
        VelibSystem result = HandleSetupCommand.handleSetup(args);
        Assertions.assertNotNull(result);
    }

    @Test
    void testHandleSetupWithFiveArguments() {
        String[] args = { "velibnetworkName", "10", "10", "4", "75" };
        VelibSystem result = HandleSetupCommand.handleSetup(args);
        Assertions.assertNotNull(result);
    }

    @Test
    void testHandleSetupWithInvalidArguments() {
        String[] args = { "invalid", "ede"};
        VelibSystem result = HandleSetupCommand.handleSetup(args);
        Assertions.assertNull(result);
    }

}
