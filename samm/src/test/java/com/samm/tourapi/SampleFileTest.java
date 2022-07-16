
package com.samm.tourapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleFileTest {

    @Test
    void contextLoads() throws IOException {
       
        // InputStream inputStream = null;
        // try {
        //     File file = new File(classLoader.getResource("/samplefestival.xml").getFile());
        //     inputStream = new FileInputStream(file);
            
        //     //...
        // }     
        // finally {
        //     if (inputStream != null) {
        //         try {
        //             inputStream.close();
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }
    }
}