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

package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses;


import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;

/**
 * The Tappy has received a command with an incorrect LCS byte.
 * This generally indicates corruption in the transmission.
 */
public class LcsMismatchErrorResponse extends com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.AbstractSystemMessage {
    public static final byte COMMAND_CODE = 0x02;

    public LcsMismatchErrorResponse() {}

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {

    }

    @Override
    public byte[] getPayload() {
        return new byte[0];
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
