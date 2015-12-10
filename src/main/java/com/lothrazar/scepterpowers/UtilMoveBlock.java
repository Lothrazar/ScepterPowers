package com.lothrazar.scepterpowers;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
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
 
	public static void moveBlockTo(World world, EntityPlayer player,BlockPos pos, BlockPos posMoveToHere)
	{
		IBlockState hit = world.getBlockState(pos);
		translateCSV();

		if(hit == null || ignoreList.contains(hit.getBlock()))
		{
			return;
		}
		
		if(world.isAirBlock(posMoveToHere) && world.isBlockModifiable(player, pos)) 
		{
			if(world.isRemote) 
			{
				spawnParticle(world, EnumParticleTypes.CRIT_MAGIC, pos); 
			}
			else
			{  
				playSoundAt(player, "random.wood_click");

				//they swap places
				//world.destroyBlock(posMoveToHere, false);
				world.destroyBlock(pos, false);
				world.setBlockState(posMoveToHere, hit, U);//pulls the block towards the player
				
				player.swingItem();
			} 
		} 
	}

	public static void playSoundAt(Entity player, String sound)
	{ 
		player.worldObj.playSoundAtEntity(player, sound, 1.0F, 1.0F);
	}
 
	public static void spawnParticle(World world, EnumParticleTypes type, BlockPos pos)
	{
		spawnParticle(world,type,pos.getX(),pos.getY(),pos.getZ());
	}

	public static void spawnParticle(World world, EnumParticleTypes type, double x, double y, double z)
	{ 
		//http://www.minecraftforge.net/forum/index.php?topic=9744.0
		for(int countparticles = 0; countparticles <= 10; ++countparticles)
		{
			world.spawnParticle(type, x + (world.rand.nextDouble() - 0.5D) * (double)0.8, y + world.rand.nextDouble() * (double)1.5 - (double)0.1, z + (world.rand.nextDouble() - 0.5D) * (double)0.8, 0.0D, 0.0D, 0.0D);
		} 
    }

	/**
	 * wrap moveBlockTo but detect the destination based on the side hit
	 * @param worldIn
	 * @param player
	 * @param pos
	 * @param face
	 */
	public static void moveBlock(World worldIn, EntityPlayer player, BlockPos pos, EnumFacing face) {
		
		BlockPos posTowardsPlayer = pos.offset(face);
		
		BlockPos posAwayPlayer = pos.offset(face.getOpposite());
		 
		BlockPos posMoveToHere = player.isSneaking() ? posTowardsPlayer : posAwayPlayer;
		
		moveBlockTo(worldIn,player,pos,posMoveToHere);
	}
}