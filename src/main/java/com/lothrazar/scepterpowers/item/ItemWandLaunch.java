package com.lothrazar.scepterpowers.item;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWandLaunch extends ItemWandBase {

	public static int DURABILITY = 999;
	public ItemWandLaunch(){
		super(DURABILITY);
	}
	private final static String NBT_MODE = "mode";
	private final static int MODE_LAUNCH = 1;
	private final static int MODE_LOOK = 2;
	private final static int MODE_UP = 3;//only/always up
	private final static int MODE_HOVER = 4;
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		
		if(playerIn.isSneaking()){
			this.toggleMode(itemStackIn);
		}
		else{
			
			//thank you REF http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/1435515-how-i-can-do-to-move-to-where-i-look
			//first reset falling/jumping
			playerIn.motionY = 0;
			playerIn.fallDistance = 0;
	
			float f = power();
			//double velX = playerIn.getLookVec().xCoord/2, velY = 0.7, velZ = playerIn.getLookVec().xCoord/2;
			double velX = (double)(-MathHelper.sin(playerIn.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(playerIn.rotationPitch / 180.0F * (float)Math.PI) * f);
			double velZ = (double)( MathHelper.cos(playerIn.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(playerIn.rotationPitch / 180.0F * (float)Math.PI) * f);
			double velY = (double)(-MathHelper.sin((playerIn.rotationPitch) / 180.0F * (float)Math.PI) * f);
			
			switch(getMode(itemStackIn)){
			case MODE_LAUNCH:
				//launch the player up and forward at minimum 30 degrees regardless of look vector
				if(velY < 0){
					velY *= -1;//first invert direction
				}
				if(velY < 0.3){
					//if you are looking straight ahead, this is zero
					
					velY = 0.3 + playerIn.jumpMovementFactor;//do a bit of a jump
				}
				break;
			case MODE_LOOK:
				//do nothing. leave y the same it is already following look vec
				break;
			case MODE_UP:
				velX = 0;
				velY = 0;
				break;
			case MODE_HOVER:
				////mode hover does nothing on cast
			break;
			}
			
			playerIn.addVelocity(velX,velY,velZ); 

		}
    	return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(this.getMode(stack) == MODE_HOVER){
			entityIn.motionY = 0;//goal is to make player not fall
		}
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		int mode = this.getMode(stack);
		
		tooltip.add("MODE:"+mode);//TODO; real lang text
		
		
		super.addInformation(stack, playerIn, tooltip, advanced);
    }
	private int getMode(ItemStack stack){
		if(!stack.hasTagCompound()){stack.setTagCompound(new NBTTagCompound());}
		
		if(!stack.getTagCompound().hasKey(NBT_MODE)){
			return MODE_LAUNCH;//not set, dont return zero
		}
		else{
			return stack.getTagCompound().getInteger(NBT_MODE);
		}
	}
	private void setMode(ItemStack stack,int mode){
		if(!stack.hasTagCompound()){stack.setTagCompound(new NBTTagCompound());}
		
		stack.getTagCompound().setInteger(NBT_MODE, mode);
	}
	private void toggleMode(ItemStack stack){
		int next = this.getMode(stack) + 1;
		next++;
		if(next == MODE_HOVER){next = MODE_LAUNCH;}//modulo increment

		System.out.println(next+ "_toggle");
		this.setMode(stack,next);
	}
	private float power(){
		return 1.5F;
	}
}
