package modele;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedago
 */
public class DiscountCode {
    
    private String code;
    private float rate;
    
    public DiscountCode(String code, float taux){
        this.code = code;
        this.rate = rate;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public float getRate(){
        return this.rate;
    }
}
