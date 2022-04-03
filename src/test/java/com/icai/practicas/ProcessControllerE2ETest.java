package com.icai.practicas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.assertj.core.api.BDDAssertions.then;

import com.icai.practicas.controller.ProcessController;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProcessControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void datos_validos_step1_then_ok() {

        String address ="http://localhost:"+port+"/api/v1/process-step1";

        String fullNameRaw ="Daniel";

        String dniRaw ="05965844J";

        String telefonoRaw ="630002214"; //MultiValuesMap, mismo funcionamiento con mismas variabñes

        HttpHeaders headers =new HttpHeaders();

        ProcessController.DataRequest data =new ProcessController.DataRequest(fullNameRaw, dniRaw, telefonoRaw);

        
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request =new HttpEntity<>(data, headers) ;

        ResponseEntity<String> result =this.restTemplate.postForEntity(address, request, String.class);
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK); //todo bien

      }

      @Test
      public void datos_validos_step1_legacy_then_ok() {
  
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        
  
          //key-value
          /*
          String fullNameRaw1 ="Daniel Losada";
          String dniRaw1 ="05965844J";
          String telefonoRaw1 ="+34 6300022145"; //datos validos
  
          String fullNameRaw2 ="Nacho Gimenez";
          String dniRaw2 ="0000000T";
          String telefonoRaw2 ="630002214"; //Dni mal
  
          String fullNameRaw3 ="Nacho Gimenez";
          String dniRaw3 ="05965845J";
          String telefonoRaw3 ="630002214984"; //Telefono mal
  
          String fullNameRaw4 ="";
          String dniRaw4 ="05965845J";
          String telefonoRaw4 ="630002214"; //Nombre mal
          */
  
          

        
        MultiValueMap<String, String> data1 =new LinkedMultiValueMap<>();
        data1.add("fullName", "Daniel Losada");
        data1.add("dni", "05965845J");
        data1.add("telefono", "+34 6300022156");

        
        MultiValueMap<String, String> data2 =new LinkedMultiValueMap<>();
        data2.add("fullName", "");
        data2.add("dni", "05965845J");
        data2.add("telefono", "+34 6300022156");

        
        MultiValueMap<String, String> data3 =new LinkedMultiValueMap<>();
        data3.add("fullName", "Daniel Losada Rueda");
        data3.add("dni", "05965845ABC");
        data3.add("telefono", "+34 6300022156");

        
        MultiValueMap<String, String> data4 = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data1, headers);
        HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(data2, headers);
        HttpEntity<MultiValueMap<String, String>> request3 = new HttpEntity<>(data3, headers);
        HttpEntity<MultiValueMap<String, String>> request4 = new HttpEntity<>(data4, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);
        ResponseEntity<String> result2 = this.restTemplate.postForEntity(address, request2, String.class);
        ResponseEntity<String> result3 = this.restTemplate.postForEntity(address, request3, String.class);
        ResponseEntity<String> result4 = this.restTemplate.postForEntity(address, request4, String.class);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result2.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result3.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result3.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(result4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        
        

    }

      
    
}
