/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1718_p2si;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 *
 * @author alex
 */
public class pruebas {
    public static void main ( String [] args )
    {
    
        Path path = Paths.get("fuerte.txt");

        try (Stream<String> stream = Files.lines(path)) {
           stream.forEach(System.out::println);
        } catch (IOException e) {
           e.printStackTrace();
        }
        
    }
}
