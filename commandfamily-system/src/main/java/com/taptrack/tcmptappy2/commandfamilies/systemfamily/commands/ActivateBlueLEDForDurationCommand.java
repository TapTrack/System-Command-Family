/*
 * Copyright (c) 2016. Papyrus Electronics, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * you may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taptrack.tcmptappy2.commandfamilies.systemfamily.commands;

import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.AbstractSystemMessage;

import java.util.Arrays;

public class ActivateBlueLEDForDurationCommand extends AbstractSystemMessage {
    public static final byte COMMAND_CODE = (byte)0x12;
    private int duration;

    public ActivateBlueLEDForDurationCommand() {
        this.duration = 0;
    }

    public ActivateBlueLEDForDurationCommand(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if (payload.length < 2) {
            throw new MalformedPayloadException("payload must be at least two bytes");
        }

        this.duration = Utils.uint16ToInt(Arrays.copyOfRange(payload,0,2));
    }

    @Override
    public byte[] getPayload() {
        return Utils.intToUint16(duration);
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
