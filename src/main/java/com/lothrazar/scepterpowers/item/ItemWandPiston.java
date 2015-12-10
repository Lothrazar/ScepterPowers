package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.lothrazar.scepterpowers.ItemRegistry;
import com.lothrazar.scepterpowers.ModScepterPowers; 
import com.lothrazar.scepterpowers.UtilMoveBlock;

public class ItemWandPiston extends ItemWandAbstract{

	public static int DURABILITY;
	public ItemWandPiston()
	{
		super();
		this.setCreativeTab(ModScepterPowers.tabSamsContent);
		this.setMaxDamage(DURABILITY);
		this.setMaxStackSize(1);
	
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		//if(worldIn.isRemote == false){
		
		BlockPos resultPosition = UtilMoveBlock.moveBlock(worldIn, playerIn, pos, side);

		if(resultPosition != null){
			//then it was a success
			spawnParticle(worldIn, EnumParticleTypes.CRIT_MAGIC, resultPosition); 
		}
			

		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    } 
 
	public static void spawnParticle(World world, EnumParticleTypes type, BlockPos pos)
	{ 
		double x=pos.getX(),y=pos.getY(),z=pos.getZ();
		
		//http://www.minecraftforge.net/forum/index.php?topic=9744.0
		for(int countparticles = 0; countparticles <= 10; ++countparticles)
		{
			world.spawnParticle(type, x + (world.rand.nextDouble() - 0.5D) * (double)0.8, y + world.rand.nextDouble() * (double)1.5 - (double)0.1, z + (world.rand.nextDouble() - 0.5D) * (double)0.8, 0.0D, 0.0D, 0.0D);
		} 
    }

	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(ItemRegistry.wand_piston), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.iron_block, 'b', Items.blaze_rod);
		
	}
}
