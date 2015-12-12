package com.lothrazar.scepterpowers.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import com.google.common.collect.ImmutableList;
import com.lothrazar.scepterpowers.Const;

public class ItemWandRotateState extends BaseWand {

	public static int DURABILITY = 999;
	//this item is creative only since it hits ALL block states
	//including wool color, stone types, slab variants, etc
	public ItemWandRotateState(){
		super(DURABILITY);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(worldIn.isRemote == true){return false;}
		if(stack == null){return false;}
		IBlockState clicked = worldIn.getBlockState(pos);
		if(clicked == null || clicked.getBlock() == null){return false;}

		ImmutableList<IBlockState> states = clicked.getBlock().getBlockState().getValidStates();

		if(states == null || states.size() <= 1){
			return false;
		}
		
		IBlockState firstState = null;
		boolean foundCurrent = false;
		IBlockState nextState = null;
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
			this.onSuccess(playerIn, stack);
		}
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    }
}
