package com.forge.revature.demo;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.forge.revature.models.Certification;

import org.junit.jupiter.api.Test;

public class CertificationTest {
    Date dateForTest = new Date();
    Certification certification = new Certification("Test", "123456", "Tester", dateForTest, "testurl");

    @Test
    public void checkCertificationCreated(){
        assertNotNull(certification);
    }
    @Test
    public void checkGetName(){
        String certName = certification.getName();
        assertEquals(certName, "Test");
    }

    @Test
    public void checkSetName(){
        certification.setName("Testing");
        assertEquals(certification.getName(), "Testing");
    }

    @Test
    public void checkGetCertId(){
        String certId = certification.getCertId();
        assertEquals(certId, "123456");
    }

    @Test
    public void checkSetCertId(){
        certification.setCertId("654321");
        assertEquals(certification.getCertId(), "654321");
    }

    @Test
    public void checkGetIssuedBy(){
        String certIssuedBy = certification.getIssuedBy();
        assertEquals(certIssuedBy, "Tester");
    }

    @Test
    public void checkSetIssuedBy(){
        certification.setIssuedBy("Steve");
        assertEquals(certification.getIssuedBy(), "Steve");
    }

    @Test
    public void checkGetIssuedOn(){
        Date date = certification.getIssuedOn();
        assertEquals(date, dateForTest);
    }

    @Test
    public void checkSetIssuedOn(){
        Date newDateForTest = new Date();
        certification.setIssuedOn(newDateForTest);;
        assertEquals(certification.getIssuedOn(), newDateForTest);
    }

    @Test
    public void checkGetPublicUrl(){
        String publicUrl = certification.getPublicUrl();
        assertEquals(publicUrl, "testurl");
    }

    @Test
    public void checkSetPublicUrl(){
        certification.setPublicUrl("newtesturl.com");
        assertEquals(certification.getPublicUrl(), "newtesturl.com");
    }
}
