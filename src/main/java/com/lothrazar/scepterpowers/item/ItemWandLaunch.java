package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWandLaunch extends ItemWandBase {

	public static int DURABILITY = 999;
	public ItemWandLaunch(){
		super(DURABILITY);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		//STill in proof of concept stage
		//on item use means only when you hit a block
		//meaning you could also hit face, get direcition, etc.
		
		//We could checkmaybe facing / looking direction
		playerIn.motionY = 0;
		playerIn.fallDistance = 0;
		playerIn.addVelocity(0, 0.7F, 0); 

    	return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }
}
