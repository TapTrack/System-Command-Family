package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.ConfigureKioskModeCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetBatteryLevelCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetHardwareVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.GetFirmwareVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.PingCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands.SetConfigItemCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.ConfigItemResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.ConfigureKioskModeResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.CrcMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.FirmwareVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.GetBatteryLevelResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.HardwareVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.ImproperMessageFormatResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.LcsMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.LengthMismatchErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.PingResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses.SystemErrorResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.CommandFamily;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

public class SystemCommandLibrary implements CommandFamily {
    public static final byte[] FAMILY_ID = new byte[]{0x00,0x00};

    @Override
    public TCMPMessage parseCommand(TCMPMessage message) throws CommandCodeNotSupportedException, MalformedPayloadException {
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
                throw new CommandCodeNotSupportedException(
                        SystemCommandLibrary.class.getSimpleName()+
                                " doesn't support response code "+String.format("%02X",message.getCommandCode()));
        }

        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    public TCMPMessage parseResponse(TCMPMessage message) throws ResponseCodeNotSupportedException, MalformedPayloadException {
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
                throw new ResponseCodeNotSupportedException(
                        SystemCommandLibrary.class.getSimpleName()+
                                " doesn't support response code "+String.format("%02X", message.getCommandCode()));
        }

        parsedMessage.parsePayload(message.getPayload());
        return parsedMessage;
    }

    @Override
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
