package com.lothrazar.scepterpowers.item;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.lothrazar.scepterpowers.util.Vector3;

public class ItemWandCollect extends ItemWandBase {

	//toggle on/off
	
	public static String NBT_ONOFF = "on_off";
	public static int DURABILITY = 999;
	public ItemWandCollect(){
		super(DURABILITY);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){

		cast(worldIn,pos);
		
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    }
	
	private void cast(World worldIn, BlockPos pos){
		int radius = 20;
		
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		
		List<EntityItem> found = worldIn.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.fromBounds(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));
		
		int moved = 0;
		for(EntityItem eitem : found){
			Vector3.setEntityMotionFromVector(eitem, x, y,z,0.4F);
			moved++;
		}
		
		if(moved > 0){
			this.onCastSuccess();
		}
	}
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		cast(worldIn,entityIn.getPosition());
		
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
	@Override
	public void addRecipe() {

		GameRegistry.addRecipe(new ItemStack(this), 
				"  p", 
				" i ", 
				"b  ", 
				'p', Blocks.piston, 'i', Blocks.clay, 'b', Items.blaze_rod);
	} 
}
