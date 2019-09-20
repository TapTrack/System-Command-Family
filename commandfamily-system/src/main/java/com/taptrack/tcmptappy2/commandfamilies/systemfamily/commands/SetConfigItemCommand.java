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

import android.support.annotation.NonNull;

import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.commandfamilies.systemfamily.AbstractSystemMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Set a configuration byte on the Tappy identified by a single byte parameter identifier
 */
public class SetConfigItemCommand extends AbstractSystemMessage {
    public static final byte COMMAND_CODE = (byte)0x01;
    private byte parameter;
    private byte[] value;

    /* For details of what these settings do, please consult the
     * Tappy command reference
     */
    public interface ParameterBytes {
        public static final byte TYPE_2_IDENTIFICATION = 0x01;
        public static final byte DATA_THROTTLING = 0x02;
        public static final byte DUAL_TYPE1_2_DECTION = 0x03;
        public static final byte BUZZER_DURATION = 0x05;
        public static final byte GREEN_LED_DURATION = 0x06;
        public static final byte RED_LED_DURATION = 0x07;
    }

    public SetConfigItemCommand() {
        parameter = (0x00);
        value = new byte[]{};
    }

    public SetConfigItemCommand(byte parameter, byte value) {
        this.parameter = parameter;
        this.value = new byte[]{value};
    }

    public SetConfigItemCommand(byte parameter, @NonNull byte[] value) {
        this.parameter = parameter;
        this.value = value;
    }

    /**
     * Get the parameter identifier this command is going to set
     * @return
     */
    public byte getParameter() {
        return parameter;
    }

    /**
     * Set the parameter identifier to change
     * @param parameter
     */
    public void setParameter(byte parameter) {
        this.parameter = parameter;
    }

    /**
     * Get the value the command will be setting, note that this will only return the first
     * value or 0x00 if the value length is 0. This is for backwards-compatibility reasons
     * to the initial version of this command which only supported single-byte values
     * @return
     */
    public byte getValue() {
        if (value.length > 0) {
            return value[0];
        } else {
            return 0x00;
        }
    }

    /**
     * Change the value this command will set. note that this will set the value to the single byte
     * specified value or 0x00 if the value length is 0. This is for backwards-compatibility reasons
     * to the initial version of this command which only supported single-byte values
     * @param value
     */
    public void setValue(byte value) {
        this.value = new byte[]{value};
    }

    /**
     * Get the value the command will be setting
     * @return
     */
    @NonNull
    public byte[] getMultibyteValue() {
        return value;
    }

    /**
     * Change the value this command will set. note that this will set the value to the single byte
     * specified value or 0x00 if the value length is 0. This is for backwards-compatibility reasons
     * to the initial version of this command which only supported single-byte values
     * @param value
     */
    public void setMultibyteValue(@NonNull  byte[] value) {
        this.value = value;
    }


    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length < 1) {
            throw new IllegalArgumentException("Payload malformed");
        }

        this.parameter = payload[0];
        if (payload.length >= 2) {
            this.value = Arrays.copyOfRange(payload,1,payload.length);
        } else {
            this.value = new byte[]{};
        }
    }

    @Override
    public byte[] getPayload() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(1+value.length);
        stream.write(parameter);
        try {
            stream.write(value);
        } catch (IOException e) {
            // this should be impossible
        }
        return stream.toByteArray();
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }

}
