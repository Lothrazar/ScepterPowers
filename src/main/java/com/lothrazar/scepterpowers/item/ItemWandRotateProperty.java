package com.lothrazar.scepterpowers.item;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.google.common.collect.ImmutableList;
import com.lothrazar.scepterpowers.Const;

public class ItemWandRotateProperty extends ItemWandBase {

	public static int DURABILITY = 999;
	public ItemWandRotateProperty(){
		super(DURABILITY);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(worldIn.isRemote == true){return false;}
		if(stack == null){return false;}
		IBlockState clicked = worldIn.getBlockState(pos);
		if(clicked == null || clicked.getBlock() == null){return false;}
	
		if(clicked.getBlock().rotateBlock(worldIn, pos, side)){
			//for example, BlockMushroom.rotateBlock uses this, and hay bales use it to swap the 'axis'
			System.out.println("rotateBlock success");
			this.onUseSuccess(playerIn, stack);
		}
		else{
			System.out.println("rotateBlock FAILS -> look into properties");
			//any property that is not variant?
			for (IProperty prop : (java.util.Set<IProperty>)clicked.getProperties().keySet())
	        {
				//since slabs do not use rotateBlock, swap the up or down half being used
	            if (prop.getName().equals("half"))
	            {
	                worldIn.setBlockState(pos, clicked.cycleProperty(prop));
	                return true;
	            }
	            //do not do variant, color, wet,  check_decay, decayable, stage, type
	            //TODO: add a whitelist where "variant" is allowed, such as sandstone ?
	        }
		}
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    }

	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(this), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.web, 'b', Items.blaze_rod);
	} 
}
