package com.lothrazar.scepterpowers;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World; 

public class UtilMoveBlock 
{ 
	public static ArrayList<Block> ignoreList = new ArrayList<Block>();
	private static String ignoreListFromConfig = "";
	public static final int U = 2;
	 
	private static void translateCSV()
	{
		//do this on the fly, could be items not around yet during config change
		if(ignoreList.size() == 0)
		{
			//ignoreList = ModControlBlocks.getBlockListFromCSV(ignoreListFromConfig); 
		
			//ignoreList.add(Blocks.bedrock);
			ignoreList.add(Blocks.end_portal_frame);
			ignoreList.add(Blocks.end_portal);
			ignoreList.add(Blocks.portal);
			ignoreList.add(Blocks.bed);
			ignoreList.add(Blocks.dark_oak_door);
			ignoreList.add(Blocks.acacia_door);
			ignoreList.add(Blocks.birch_door);
			ignoreList.add(Blocks.oak_door);
			ignoreList.add(Blocks.spruce_door);
			ignoreList.add(Blocks.jungle_door);
			ignoreList.add(Blocks.iron_door);
			ignoreList.add(Blocks.skull);
		}
	}
	
	public static void seIgnoreBlocksFromString(String csv)
	{ 
		ignoreListFromConfig = csv;
	} 
 
	public static boolean moveBlockTo(World world, EntityPlayer player,BlockPos pos, BlockPos posMoveToHere)
	{
		IBlockState hit = world.getBlockState(pos);
		translateCSV();

		if(hit == null || ignoreList.contains(hit.getBlock()))
		{
			return false;
		}
		
		if(world.isAirBlock(posMoveToHere) && world.isBlockModifiable(player, pos)) 
		{
		 
			//playSoundAt(player, "random.wood_click");

			//they swap places
			//world.destroyBlock(posMoveToHere, false);
			if(world.isRemote == false){//just to avoid duplicating the sound effect
			
				world.destroyBlock(pos, false);
			}
			world.setBlockState(posMoveToHere, hit, U);//pulls the block towards the player
			world.markBlockForUpdate(posMoveToHere);
			player.swingItem();
	 
			return true;
		} 
		else return false;
	}

	/**
	 * wrap moveBlockTo but detect the destination based on the side hit
	 * @param worldIn
	 * @param player
	 * @param pos
	 * @param face
	 */
	public static BlockPos moveBlock(World worldIn, EntityPlayer player, BlockPos pos, EnumFacing face) {
		
		BlockPos posTowardsPlayer = pos.offset(face);
		
		BlockPos posAwayPlayer = pos.offset(face.getOpposite());
		 
		BlockPos posMoveToHere = player.isSneaking() ? posTowardsPlayer : posAwayPlayer;
		
		if(moveBlockTo(worldIn,player,pos,posMoveToHere)){
			return posMoveToHere;
		}
		else{
			return null;
		}
	}
}