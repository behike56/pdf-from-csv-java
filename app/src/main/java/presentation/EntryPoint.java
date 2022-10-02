package presentation;

import usecase.PdfConverter;

public class EntryPoint {
    
    public static void main(String[] args) {
        
        try {

            PdfConverter.convert();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
