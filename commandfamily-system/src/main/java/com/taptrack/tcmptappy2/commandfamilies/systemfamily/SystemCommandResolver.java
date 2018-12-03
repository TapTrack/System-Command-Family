package com.taptrack.tcmptappy2.commandfamilies.systemfamily;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;

import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.ConfigureKioskModeCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetBatteryLevelCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetFirmwareVersionCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.GetHardwareVersionCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.PingCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands.SetConfigItemCommand;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ConfigItemResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ConfigureKioskModeResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.CrcMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.FirmwareVersionResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.GetBatteryLevelResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.HardwareVersionResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.ImproperMessageFormatResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.LcsMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.LengthMismatchErrorResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.PingResponse;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.responses.SystemErrorResponse;
import com.taptrack.tcmptappy2.CommandFamilyMessageResolver;
import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.TCMPMessage;

import java.util.Arrays;

public class SystemCommandResolver implements CommandFamilyMessageResolver {
    public static final byte[] FAMILY_ID = new byte[]{0x00,0x00};

    private static void assertFamilyMatches(@NonNull TCMPMessage message) {
        if (!Arrays.equals(message.getCommandFamily(),FAMILY_ID)) {
            throw new IllegalArgumentException("Specified message is for a different command family");
        }
    }

    @Override
    @Nullable
    public TCMPMessage resolveCommand(@NonNull TCMPMessage message) throws MalformedPayloadException {
        assertFamilyMatches(message);

        TCMPMessage parsedMessage;
        switch(message.getCommandCode()) {
            case GetHardwareVersionCommand.COMMAND_CODE:
                parsedMessage = new GetHardwareVersionCommand();
                break;

            case GetFirmwareVersionCommand.COMMAND_CODE:
                parsedMessage = new GetFirmwareVersionCommand();
                break;

            case GetBatteryLevelCommand.COMMAND_CODE:
                parsedMessage = new GetBatteryLevelCommand();
                break;

            case PingCommand.COMMAND_CODE:
                parsedMessage = new PingCommand();
                break;

            case SetConfigItemCommand.COMMAND_CODE:
                parsedMessage = new SetConfigItemCommand();
                break;

            case ConfigureKioskModeCommand.COMMAND_CODE:
                parsedMessage = new ConfigureKioskModeCommand();
                break;

            default:
                return null;
        }

        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    @Nullable
    public TCMPMessage resolveResponse(@NonNull TCMPMessage message) throws MalformedPayloadException {
        assertFamilyMatches(message);

        TCMPMessage parsedMessage;
        switch(message.getCommandCode()) {
            case ConfigItemResponse.COMMAND_CODE:
                parsedMessage = new ConfigItemResponse();
                break;

            case ConfigureKioskModeResponse.COMMAND_CODE:
                parsedMessage = new ConfigureKioskModeResponse();
                break;

            case CrcMismatchErrorResponse.COMMAND_CODE:
                parsedMessage = new CrcMismatchErrorResponse();
                break;

            case FirmwareVersionResponse.COMMAND_CODE:
                parsedMessage = new FirmwareVersionResponse();
                break;

            case GetBatteryLevelResponse.COMMAND_CODE:
                parsedMessage = new GetBatteryLevelResponse();
                break;

            case HardwareVersionResponse.COMMAND_CODE:
                parsedMessage = new HardwareVersionResponse();
                break;

            case ImproperMessageFormatResponse.COMMAND_CODE:
                parsedMessage = new ImproperMessageFormatResponse();
                break;

            case LcsMismatchErrorResponse.COMMAND_CODE:
                parsedMessage = new LcsMismatchErrorResponse();
                break;

            case LengthMismatchErrorResponse.COMMAND_CODE:
                parsedMessage = new LengthMismatchErrorResponse();
                break;

            case PingResponse.COMMAND_CODE:
                parsedMessage = new PingResponse();
                break;

            case SystemErrorResponse.COMMAND_CODE:
                parsedMessage = new SystemErrorResponse();
                break;

            default:
                return null;
        }

        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    @NonNull
    @Size(2)
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
