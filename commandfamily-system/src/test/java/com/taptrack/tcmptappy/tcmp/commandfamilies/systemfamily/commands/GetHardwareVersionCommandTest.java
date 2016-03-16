package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetHardwareVersionCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        GetHardwareVersionCommand command = new GetHardwareVersionCommand();
        assertEquals(command.getCommandCode(),(byte)0xFF);
    }
}