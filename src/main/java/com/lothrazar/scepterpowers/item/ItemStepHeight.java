package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStepHeight extends ItemWandBase {

	//toggle on/off
	
	public static int DURABILITY = 999;
	public ItemStepHeight(){
		super(DURABILITY);
	}
 
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    { 
		if(entityIn instanceof EntityPlayer && ((EntityPlayer)entityIn).inventory.currentItem == itemSlot){

			//or we could also require its in armor slot or something too

			//TODO: always have to hold it? an equip slot? something else?
			//durability loss, exp, sound, and so on?
			
			((EntityPlayer)entityIn).stepHeight = 1F;

		}
		
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
