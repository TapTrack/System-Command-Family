package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SetConfigItemCommandTest {

    private byte[] generateTestPayload(byte configItem, byte value) {
        return new byte[]{configItem,value};
    }

    @Test
    public void testParsePayload() throws Exception {
        byte item = 0x45;
        byte value = 0x72;
        byte[] testPayload = generateTestPayload(item,value);

        SetConfigItemCommand command = new SetConfigItemCommand();
        command.parsePayload(testPayload);

        assertEquals(command.getParameter(), item);
        assertEquals(command.getValue(),value);
    }

    @Test
    public void testGetPayload() throws Exception {
        byte item = 0x45;
        byte value = 0x72;
        byte[] testPayload = generateTestPayload(item,value);

        SetConfigItemCommand command = new SetConfigItemCommand(item,value);

        assertArrayEquals(testPayload,command.getPayload());
    }

    @Test
    public void testGetCommandCode() throws Exception {
        SetConfigItemCommand command = new SetConfigItemCommand();
        assertEquals(command.getCommandCode(),0x01);
    }
}