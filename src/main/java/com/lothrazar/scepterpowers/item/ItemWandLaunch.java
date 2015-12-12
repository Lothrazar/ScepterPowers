package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
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
		//thank you REF http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/1435515-how-i-can-do-to-move-to-where-i-look
		//first reset falling/jumping
		playerIn.motionY = 0;
		playerIn.fallDistance = 0;

		float f = 1.0F;
		//double velX = playerIn.getLookVec().xCoord/2, velY = 0.7, velZ = playerIn.getLookVec().xCoord/2;
		double velX = (double)(-MathHelper.sin(playerIn.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(playerIn.rotationPitch / 180.0F * (float)Math.PI) * f);
		double velZ = (double)(MathHelper.cos(playerIn.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(playerIn.rotationPitch / 180.0F * (float)Math.PI) * f);
		double velY = (double)(-MathHelper.sin((playerIn.rotationPitch) / 180.0F * (float)Math.PI) * f);
	

		//TODO: if shifting, only up? or something
		//or we could do different modes
		//currently velY sends to the exact y look direction
		System.out.println(velY);
		if(velY < 0){
			velY *= -1;//first invert direction
		}
		if(velY < 0.3){
			//if you are looking straight ahead, this is zero
			
			velY = 0.3 + playerIn.jumpMovementFactor;//do a bit of a jump
		}
		System.out.println(velY);
		System.out.println("==");
		//We could checkmaybe facing / looking direction
		//maybe setVelocity?
		playerIn.addVelocity(velX,velY,velZ); 

    	return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }
}
