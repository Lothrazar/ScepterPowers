package com.lothrazar.scepterpowers.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import com.lothrazar.scepterpowers.ItemRegistry;

public class ItemWandChest extends ItemWandBase {

	public static int DURABILITY = 999;
	public ItemWandChest() {
		super(DURABILITY);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		//imported from my old mod https://github.com/PrinceOfAmber/SamsPowerups/blob/b02f6b4243993eb301f4aa2b39984838adf482c1/src/main/java/com/lothrazar/samscontent/item/ItemChestSackEmpty.java
	
		if(worldIn.getTileEntity(pos) == null || worldIn.getTileEntity(pos) instanceof IInventory == false){return false;}
			
		
		TileEntity tile = worldIn.getTileEntity(pos);
		IInventory invo = (IInventory)tile;
		
		ItemStack chestItem;   
		int ROWS = 3;
		int COLS = 9;
		int START_CHEST = 0; 
		int END_CHEST =  START_CHEST + ROWS * COLS; 

		ItemStack drop = new ItemStack(ItemRegistry.item_chestsack ,1,0); 
		
		if(drop.getTagCompound() == null)  drop.setTagCompound(new NBTTagCompound());
 
		int stacks = 0;
		int count = 0;
		
		int[] itemids = new int[END_CHEST - START_CHEST];
		int[] itemqty = new int[END_CHEST - START_CHEST];		
		int[] itemdmg = new int[END_CHEST - START_CHEST];
		
		//inventory and chest has 9 rows by 3 columns, never changes. same as 64 max stack size
		for(int islotChest = 0; islotChest < invo.getSizeInventory(); islotChest++)
		{
			//zeroes to avoid nulls, and signify nothing goes there
			itemids[islotChest] = 0;
			itemqty[islotChest] = 0;
			itemids[islotChest] = 0;
			chestItem = invo.getStackInSlot(islotChest);
		
			if(chestItem == null){continue;}//not an error; empty chest slot
			if(chestItem.getTagCompound() != null)
			{
				//probably has an enchantment
				playerIn.dropPlayerItemWithRandomChoice(chestItem, false); 
			}
			else
			{
				stacks++; 
				count += chestItem.stackSize;
				
				itemids[islotChest] = Item.getIdFromItem(chestItem.getItem());
				itemdmg[islotChest] = chestItem.getItemDamage(); 
				itemqty[islotChest] = chestItem.stackSize;
				
			}
			//its either in the bag, or dropped on the player
			invo.setInventorySlotContents(islotChest, null);	
		}
		 
		if(drop.getTagCompound() == null) drop.setTagCompound(new NBTTagCompound());
		
		drop.getTagCompound().setIntArray("itemids", itemids);
		drop.getTagCompound().setIntArray("itemdmg", itemdmg);
		drop.getTagCompound().setIntArray("itemqty", itemqty);
		 
		drop.getTagCompound().setString("count",""+count);
		drop.getTagCompound().setString("stacks",""+stacks);
	 	 
		if(worldIn.isRemote == false){
			playerIn.entityDropItem(drop, 1); 
		}
		 //the 2 here is just a magic flag it passes to the world to propogate the event
	
		playerIn.worldObj.setBlockToAir(pos); 

		//playerIn.swingItem();
	
		//Util.spawnParticle(entityPlayer.worldObj, EnumParticleTypes.CRIT, pos);
		
		//Util.playSoundAt(entityPlayer, "random.wood_click");
		
 
    	return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ); 
    } 
}
