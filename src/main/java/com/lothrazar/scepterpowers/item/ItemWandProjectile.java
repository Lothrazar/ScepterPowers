package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.lothrazar.scepterpowers.projectile.EntityBlazeBolt;

public class ItemWandProjectile extends BaseWand{

	//steal some ideas from EnderProjectiles

	public static int DURABILITY = 999;
	public ItemWandProjectile(){
		super(DURABILITY);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn){
		
		if(worldIn.isRemote == false){
			worldIn.spawnEntityInWorld(new EntityBlazeBolt(worldIn, playerIn));
		}
		this.onSuccess(playerIn, stack);
		
		return super.onItemRightClick(stack, worldIn, playerIn);
	}
}
