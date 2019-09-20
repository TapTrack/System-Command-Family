package com.taptrack.tcmptappy2.commandfamilies.systemfamily;

import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.TCMPMessage;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.ConfigureKioskModeCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.ConfigureOnboardScanCooldownCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetBatteryLevelCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetFirmwareVersionCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetHardwareVersionCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.PingCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.SetConfigItemCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ConfigItemResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ConfigureKioskModeResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ConfigureOnboardScanCooldownResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.CrcMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.FirmwareVersionResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.GetBatteryLevelResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.HardwareVersionResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ImproperMessageFormatResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.LcsMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.LengthMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.SystemErrorResponse;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SystemCommandResolverTest {
    private SystemCommandResolver library = new SystemCommandResolver();
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
        assertTrue(testCommandSupported(new ConfigureKioskModeCommand(),ConfigureKioskModeCommand.class));
        assertTrue(testCommandSupported(new GetBatteryLevelCommand(),GetBatteryLevelCommand.class));
        assertTrue(testCommandSupported(new GetFirmwareVersionCommand(),GetFirmwareVersionCommand.class));
        assertTrue(testCommandSupported(new GetHardwareVersionCommand(),GetHardwareVersionCommand.class));
        assertTrue(testCommandSupported(new PingCommand(),PingCommand.class));
        assertTrue(testCommandSupported(new SetConfigItemCommand(),SetConfigItemCommand.class));
        assertTrue(testCommandSupported(new ConfigureOnboardScanCooldownCommand(),ConfigureOnboardScanCooldownCommand.class));

        assertFalse(testCommandSupported(new FakeCommand(),FakeCommand.class));
    }

    private boolean testCommandSupported(TCMPMessage message, Class<? extends TCMPMessage> clazz)
            throws MalformedPayloadException {
        TCMPMessage parsedMessage = library.resolveCommand(message);
        if (parsedMessage != null) {
            assertThat(parsedMessage,instanceOf(clazz));
            assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testParseResponse() throws Exception {
        assertTrue(testResponseSupported(new ConfigItemResponse(),ConfigItemResponse.class));
        assertTrue(testResponseSupported(new ConfigureKioskModeResponse(),ConfigureKioskModeResponse.class));
        assertTrue(testResponseSupported(new CrcMismatchErrorResponse(),CrcMismatchErrorResponse.class));
        assertTrue(testResponseSupported(new FirmwareVersionResponse(),FirmwareVersionResponse.class));
        assertTrue(testResponseSupported(new GetBatteryLevelResponse(),GetBatteryLevelResponse.class));
        assertTrue(testResponseSupported(new HardwareVersionResponse(), HardwareVersionResponse.class));
        assertTrue(testResponseSupported(new ImproperMessageFormatResponse(), ImproperMessageFormatResponse.class));
        assertTrue(testResponseSupported(new LcsMismatchErrorResponse(), LcsMismatchErrorResponse.class));
        assertTrue(testResponseSupported(new LengthMismatchErrorResponse(), LengthMismatchErrorResponse.class));
        assertTrue(testResponseSupported(new ConfigureOnboardScanCooldownResponse(), ConfigureOnboardScanCooldownResponse.class));
        assertTrue(testResponseSupported(new SystemErrorResponse(), SystemErrorResponse.class));

        assertFalse(testResponseSupported(new FakeResponse(), FakeResponse.class));
    }

    private boolean testResponseSupported(TCMPMessage message,Class<? extends TCMPMessage> clazz)
            throws MalformedPayloadException {
        TCMPMessage parsedMessage = library.resolveResponse(message);
        if (parsedMessage != null) {
            assertThat(parsedMessage,instanceOf(clazz));
            assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testGetCommandFamilyId() throws Exception {
        byte[] familyID = library.getCommandFamilyId();
        assertNotNull(familyID);
        assertArrayEquals(new byte[]{0x00,0x00},familyID);
    }
}