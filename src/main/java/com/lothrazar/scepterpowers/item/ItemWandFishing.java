package com.lothrazar.scepterpowers.item;

import com.lothrazar.scepterpowers.projectile.EntityFishingBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWandFishing extends BaseWand {

	public static int DURABILITY = 999;
	public ItemWandFishing(){
		super(DURABILITY);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn){
		
		if(worldIn.isRemote == false){
			worldIn.spawnEntityInWorld(new EntityFishingBolt(worldIn, playerIn));
		}
		this.onSuccess(playerIn, stack);
		
		return super.onItemRightClick(stack, worldIn, playerIn);
	}
}
