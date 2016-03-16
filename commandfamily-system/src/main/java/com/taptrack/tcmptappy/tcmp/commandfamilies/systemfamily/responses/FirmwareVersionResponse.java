package com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.StandardLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.StandardLibraryVersionResponseDelegate;
import com.taptrack.tcmptappy.tcmp.commandfamilies.systemfamily.AbstractSystemMessage;

public class FirmwareVersionResponse extends AbstractSystemMessage implements StandardLibraryVersionResponse {
    public static final byte COMMAND_CODE = 0x06;
    private StandardLibraryVersionResponseDelegate delegate;

    public FirmwareVersionResponse() {
        delegate = new StandardLibraryVersionResponseDelegate();
    }
    public FirmwareVersionResponse(byte majorVersion, byte minorVersion) {
        delegate = new StandardLibraryVersionResponseDelegate(majorVersion,minorVersion);
    }

    @Override
    public byte getMajorVersion() {
        return delegate.getMajorVersion();
    }

    @Override
    public void setMajorVersion(byte majorVersion) {
        delegate.setMajorVersion(majorVersion);
    }

    @Override
    public byte getMinorVersion() {
        return delegate.getMinorVersion();
    }

    @Override
    public void setMinorVersion(byte minorVersion) {
        delegate.setMinorVersion(minorVersion);
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        delegate.parsePayload(payload);
    }

    @Override
    public byte[] getPayload() {
        return delegate.getPayload();
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
