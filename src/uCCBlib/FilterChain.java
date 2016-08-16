/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uCCBlib;

/**
 *
 * @author PLLUJUR1
 */
public class FilterChain {
              
    public int Number;
    public int Bank;
    
    public int Activation;
    public int MaskMode;
    public int Scale;
    public int FIFONr;
    
    public int id;
    public int  idRTR,idEx;
    
    public int mask;
    public int mRTR,mEx;
    
    /**
     * Create filter structure
     * 
     * See STM32F0 manual page for additional information
     * @param number Filter number in STM32F0 register, digit 0 - 15
     * @param bank   Bank number 0 - 15
     * @param activation Enabled filter 1 enabled 0 disabled
     * @param maskmode write 1 Id filtering,  0 to Enabled Mask Mode
     * @param scale write 1 for one 32 bit filter, for two 16 bit filters 0
     * @param fifonr FIFO used by filtered frame 0 or 1
     */
    public FilterChain(int number, int bank, int activation, 
            int maskmode, int scale, int fifonr, 
            int vid, int idrtr, int idex,
            int vmask, int mrtr, int mex)
    {
        Number = number;
        Bank = bank;
        Activation = activation;
        MaskMode = maskmode;
        Scale = scale;
        FIFONr = fifonr;
        
        id = vid;
        idRTR = idrtr;
        idEx = idex;
        
        mask = vmask;
        mRTR = mrtr;
        mEx = mex;          
    }
    
    @Override
    public String toString() { 
        String ret = "";
        int flag = 0;
        // Filter ID Number and Bank number 
        ret = String.format("%02x", Number);
        ret += String.format("%02x", Bank);
        // Flags
        flag =  (Activation<<0) + (MaskMode<<1) + (Scale<<2) + (FIFONr<<3);
        ret += String.format("%01x", flag);
        // ID and idFlags
        ret += String.format("%08x", id);
        flag = idRTR<<1 + idEx;
        ret += String.format("%01x", flag);
        // Mask and maskFlags
        ret += String.format("%08x", mask);
        flag = mRTR<<1 + mEx;
        ret += String.format("%01x", flag);
        
        return ret;
    }
    
}
