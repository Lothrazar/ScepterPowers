package com.lothrazar.scepterpowers.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.google.common.collect.ImmutableList;
import com.lothrazar.scepterpowers.Const;

public class ItemWandRotate extends ItemWandBase {

	public ItemWandRotate(){
		super();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(worldIn.isRemote == true){return false;}
		if(stack == null){return false;}
		IBlockState clicked = worldIn.getBlockState(pos);
		if(clicked == null || clicked.getBlock() == null){return false;}
//	System.out.println(clicked.getClass().getName());  net.minecraft.block.state.BlockState$StateImplementation
		ImmutableList<IBlockState> states = clicked.getBlock().getBlockState().getValidStates();

		if(states == null || states.size() <= 1){
			return false;
		}
		
		IBlockState firstState = null;
		boolean foundCurrent = false;
		IBlockState nextState = null;
		
		//successfully found a list of all block states
		//int i = 0;
		for(IBlockState s : states){
			if(firstState == null){firstState = s;}
			
			if(foundCurrent){ //found current last run
				nextState = s;
				break;
			}
			
			if(s.equals(clicked)){
				foundCurrent = true;
			}
		}
		
		if(nextState == null){
			nextState = firstState;
		}
	
		if(nextState != null){
			worldIn.setBlockState(pos, nextState, Const.NOTIFY);
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
