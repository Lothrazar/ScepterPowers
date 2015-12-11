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

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		
		if(worldIn.isRemote == true){return false;}
		if(stack == null){return false;}
		IBlockState clicked = worldIn.getBlockState(pos);
		if(clicked == null || clicked.getBlock() == null){return false;}
		
		System.out.println("property hit");
		
		//copied from BlockMushroom.rotateBlock
		//clicked.getProperties()
		/* IBlockState state = world.getBlockState(pos);
        for (IProperty prop : (java.util.Set<IProperty>)state.getProperties().keySet())
        {
            if (prop.getName().equals("variant"))
            {
                world.setBlockState(pos, state.cycleProperty(prop));
                return true;
            }
        }*/
		
//	System.out.println(clicked.getClass().getName());  net.minecraft.block.state.BlockState$StateImplementation
	
		if(clicked.getBlock().rotateBlock(worldIn, pos, side)){

			this.onUseSuccess(playerIn, stack);
		}
		else{
			System.out.println("rotateBlock FAILS -> look into properties");
			//any property that is not variant?
			
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
