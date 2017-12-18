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

import com.taptrack.tcmptappy2.commandfamilies.systemfamily.AbstractSystemMessage;
import com.taptrack.tcmptappy2.MalformedPayloadException;

/**
 * Set a configuration byte on the Tappy identified by a single byte parameter identifier
 */
public class SetConfigItemCommand extends AbstractSystemMessage {
    public static final byte COMMAND_CODE = (byte)0x01;
    private byte parameter;
    private byte value;

    public SetConfigItemCommand() {
        parameter = (0x01);
        value = 0x00;
    }

    public SetConfigItemCommand(byte parameter, byte value) {
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
     * Get the value the command will be setting
     * @return
     */
    public byte getValue() {
        return value;
    }

    /**
     * Change the value this command will set
     * @param value
     */
    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length != 2)
            throw new IllegalArgumentException("Payload malformed");

        this.parameter = payload[0];
        this.value = payload[1];
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{parameter,value};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
