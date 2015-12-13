package com.lothrazar.scepterpowers.util;

import com.lothrazar.scepterpowers.ModScepterPowers;
import com.lothrazar.scepterpowers.potion.MessagePotion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class UtilParticle {
	
	public static void spawnParticle(World world,EnumParticleTypes sparkle,  double x, double y, double z, int count)
	{ 
		//http://www.minecraftforge.net/forum/index.php?topic=9744.0
		for(int countparticles = 0; countparticles <= count; ++countparticles)
		{
			world.spawnParticle(sparkle, x + (world.rand.nextDouble() - 0.5D) * (double)0.8, y + world.rand.nextDouble() * (double)1.5 - (double)0.1, z + (world.rand.nextDouble() - 0.5D) * (double)0.8
					, 0.0D, 0.0D, 0.0D);
		}
	}
	public static void spawnParticle(World world,EnumParticleTypes sparkle,  double x, double y, double z)
	{ 
		spawnParticle(world,sparkle,x,y,z,12);//default spam count
	}
	public static void spawnParticle(World world,EnumParticleTypes sparkle, BlockPos pos)
	{ 
		spawnParticle(world,sparkle,pos.getX(),pos.getY(),pos.getZ());
	}
	
	public static void spawnParticlePacket(BlockPos position, int particleID)
	{
		//this. fires only on server side. so send packet for client to spawn particles and so on
		ModScepterPowers.network.sendToAll(new MessagePotion(position, particleID));
    	
		
	}
}
