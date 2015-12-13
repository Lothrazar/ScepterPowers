package com.lothrazar.scepterpowers;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import com.lothrazar.scepterpowers.projectile.*;

public class ProjectileRegistry {

	public static void register(){

    	int entityID = 777;
    	 int trackingRange = 64; 
    	 int updateFrequency = 1;
    	 boolean sendsVelocityUpdates = true;
    	 
        EntityRegistry.registerModEntity(EntityLightningballBolt.class, "lightningbolt",entityID++, ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityHarvestBolt.class, "harvestbolt",entityID++, 		ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityWaterBolt.class, "waterbolt",entityID++, 			ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntitySnowballBolt.class, "frostbolt",entityID++, 			ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityTorchBolt.class, "torchbolt",entityID++, 			ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityShearingBolt.class, "woolbolt",entityID++, 			ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityFishingBolt.class, "fishingbolt",entityID++, 		ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityDynamite.class, "tntbolt",entityID++, 				ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(EntityBlazeBolt.class, "tntbolt",entityID++, 				ModScepterPowers.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        //EntityRegistry.registerModEntity(EntityHomeBolt.class, "bedbolt",entityID++, ModScepterPowers.instance, 64, 1, true);
        //EntityRegistry.registerModEntity(EntityDungeonEye.class, "dungeonbolt",entityID++, ModScepterPowers.instance, 64, 1, true);
	}
}
