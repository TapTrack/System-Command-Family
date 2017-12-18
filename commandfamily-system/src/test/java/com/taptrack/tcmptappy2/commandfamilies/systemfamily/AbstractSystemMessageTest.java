package com.taptrack.tcmptappy2.commandfamilies.systemfamily;


import com.taptrack.tcmptappy2.MalformedPayloadException;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AbstractSystemMessageTest {

    @Test
    public void testGetCommandFamily() throws Exception {
        AbstractSystemMessage testMessage = new AbstractSystemMessage() {
            @Override
            public void parsePayload(byte[] payload) throws MalformedPayloadException {

            }

            @Override
            public byte[] getPayload() {
                return new byte[0];
            }

            @Override
            public byte getCommandCode() {
                return 0;
            }
        };

        assertArrayEquals(testMessage.getCommandFamily(),new byte[]{0x00,0x00});
    }
}