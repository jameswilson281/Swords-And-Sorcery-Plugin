/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.monstuhs.swordsandsorcery.Models;

/**
 *
 * @author James
 */
public class SaSPlayerData {
    public String Name;
    public int MeleeLevel;
    public int ArcheryLevel;
    public int DefenseLevel;
    public int SorceryLevel;
    public int ManaPool;
    public int ManaRegenAmount;
    
    public void PlayerData(String name){
        Name = name;
    }
}