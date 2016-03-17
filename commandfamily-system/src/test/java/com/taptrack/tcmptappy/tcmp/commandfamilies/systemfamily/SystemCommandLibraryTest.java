package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetBatteryLevelCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetFirmwareVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetHardwareVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.PingCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.SetConfigItemCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.ConfigItemResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.CrcMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.FirmwareVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.GetBatteryLevelResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.HardwareVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.ImproperMessageFormatResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.LcsMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.LengthMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.SystemErrorResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SystemCommandLibraryTest {
    private SystemCommandLibrary library = new SystemCommandLibrary();
    private static class FakeCommand extends AbstractSystemMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    private static class FakeResponse extends AbstractSystemMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }
    @Test
    public void testParseCommand() throws Exception {
        testCommand(new GetBatteryLevelCommand(),GetBatteryLevelCommand.class);
        testCommand(new GetFirmwareVersionCommand(),GetFirmwareVersionCommand.class);
        testCommand(new GetHardwareVersionCommand(),GetHardwareVersionCommand.class);
        testCommand(new PingCommand(),PingCommand.class);
        testCommand(new SetConfigItemCommand(),SetConfigItemCommand.class);

        boolean commandCodeNotSupportedThrown = false;
        try {
            testCommand(new FakeCommand(),FakeCommand.class);
        }
        catch (CommandCodeNotSupportedException e) {
            commandCodeNotSupportedThrown = true;
        }

        assertTrue(commandCodeNotSupportedThrown);
    }

    private void testCommand(TCMPMessage message,Class<? extends TCMPMessage> clazz)
            throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseCommand(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
    }

    @Test
    public void testParseResponse() throws Exception {
        testResponse(new ConfigItemResponse(),ConfigItemResponse.class);
        testResponse(new CrcMismatchErrorResponse(),CrcMismatchErrorResponse.class);
        testResponse(new FirmwareVersionResponse(),FirmwareVersionResponse.class);
        testResponse(new GetBatteryLevelResponse(),GetBatteryLevelResponse.class);
        testResponse(new HardwareVersionResponse(), HardwareVersionResponse.class);
        testResponse(new ImproperMessageFormatResponse(), ImproperMessageFormatResponse.class);
        testResponse(new LcsMismatchErrorResponse(), LcsMismatchErrorResponse.class);
        testResponse(new LengthMismatchErrorResponse(), LengthMismatchErrorResponse.class);
        testResponse(new SystemErrorResponse(), SystemErrorResponse.class);

        boolean responseCodeNotSupportedThrown = false;
        try {
            testResponse(new FakeResponse(), FakeResponse.class);
        }
        catch (ResponseCodeNotSupportedException e) {
            responseCodeNotSupportedThrown = true;
        }

        assertTrue(responseCodeNotSupportedThrown);
    }

    private void testResponse(TCMPMessage message,Class<? extends TCMPMessage> clazz)
            throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseResponse(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
    }

    @Test
    public void testGetCommandFamilyId() throws Exception {

    }
}