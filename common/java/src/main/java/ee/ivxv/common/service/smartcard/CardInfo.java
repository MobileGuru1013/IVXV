package ee.ivxv.common.service.smartcard;

import ee.ivxv.common.asn1.ASN1DecodingException;
import ee.ivxv.common.asn1.Field;
import ee.ivxv.common.asn1.Sequence;

public class CardInfo {
    private final String id;
    public final static byte[] IDENTIFIER = "CARDINFO".getBytes();

    public CardInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static CardInfo create(byte[] file) throws SmartCardException {
        Sequence s = new Sequence();
        try {
            s.readFromBytes(file);
        } catch (ASN1DecodingException e) {
            throw new SmartCardException("Error reading card info blob: " + e.toString());
        }
        byte[][] fields;
        try {
            fields = s.getBytes();
        } catch (ASN1DecodingException e) {
            throw new SmartCardException("Error decoding blob: " + e.toString());
        }
        if (fields.length != 1) {
            throw new SmartCardException("Invalid format for card info blob");
        }
        Field idField = new Field();
        try {
            idField.readFromBytes(fields[0]);
        } catch (ASN1DecodingException e) {
            throw new SmartCardException("Invalid field: " + e.toString());
        }
        String id;
        try {
            id = idField.getString();
        } catch (ASN1DecodingException e) {
            throw new SmartCardException("Error while decoding index field: " + e.toString());
        }
        return new CardInfo(id);
    }

    public byte[] encode() {
        return new Sequence(new Field(id).encode()).encode();
    }
}
