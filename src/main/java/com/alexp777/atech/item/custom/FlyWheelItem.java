package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.util.Material;
import com.alexp777.atech.util.ModValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyWheelItem extends Item {

	private Material material;

	public FlyWheelItem(Material material, int durability) {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.durability(durability));
		this.material = material;
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
		super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
		if(!pLevel.isClientSide()) {
			if(pStack.getTag() != null && pSlotId == 1) {
				setTemperature(pStack, getTemperature(pStack) - 1);
			}

		}
	}

	//Overrides

	@Override
	public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
		super.onCraftedBy(pStack, pLevel, pPlayer);
		if(pStack.getTag() != null) {
			CompoundTag nbtData = new CompoundTag();
			nbtData.putInt(ModValue.FLY_WHEEL_TEMPERATURE_TAG, 50);
			nbtData.putInt(ModValue.FLY_WHEEL_DURABILITY_TAG, pStack.getMaxDamage());
			nbtData.putDouble(ModValue.FLY_WHEEL_MODIFIER_TAG, 2.5);
			pStack.setTag(nbtData);
		}
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		setTemperature(stack, 1000);
		return super.initCapabilities(stack, nbt);
	}



	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		pTooltipComponents.add(new TextComponent("§9Durability: §r" + getDurability(pStack)));
		pTooltipComponents.add(new TextComponent("§9Modifier: §r" + getModifier(pStack)));
		pTooltipComponents.add(new TextComponent("§9Temperature: §r" + getTemperature(pStack)));
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		if(pContext.getLevel().isClientSide())
			setTemperature(pContext.getItemInHand(), 50);

		return super.useOn(pContext);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if(pLevel.isClientSide()) {
			setDurability(pPlayer.getItemInHand(pUsedHand), pPlayer.getOnPos().getX());
			setDamage(pPlayer.getItemInHand(pUsedHand), getMaxDamage(pPlayer.getItemInHand(pUsedHand)) - getDurability(pPlayer.getItemInHand(pUsedHand)));
		}

		return super.use(pLevel, pPlayer, pUsedHand);
	}

	//Custom methods
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	//=== NBT Tags ===
	public double getModifier(ItemStack itemStack) {
		if(itemStack.getTag() != null
				&& itemStack.getTag().contains(ModValue.FLY_WHEEL_MODIFIER_TAG))
			return itemStack.getTag().getDouble(ModValue.FLY_WHEEL_MODIFIER_TAG);
		return 0;
	}

	public void setModifier(ItemStack itemStack, double amount) {
		if(itemStack.getTag() != null)
			itemStack.getTag().putDouble(ModValue.FLY_WHEEL_MODIFIER_TAG, amount);
	}

	public int getTemperature(ItemStack itemStack) {
		if(itemStack.getTag() != null
				&& itemStack.getTag().contains(ModValue.FLY_WHEEL_TEMPERATURE_TAG))
			return itemStack.getTag().getInt(ModValue.FLY_WHEEL_TEMPERATURE_TAG);
		return 0;
	}

	public void setTemperature(ItemStack itemStack, int amount) {
		if(itemStack.getTag() != null)
			itemStack.getTag().putInt(ModValue.FLY_WHEEL_TEMPERATURE_TAG, amount);

	}

	public int getDurability(ItemStack itemStack) {
		if(itemStack.getTag() != null
			&& itemStack.getTag().contains(ModValue.FLY_WHEEL_DURABILITY_TAG))
			return itemStack.getTag().getInt(ModValue.FLY_WHEEL_DURABILITY_TAG);
		return 0;
	}

	public void setDurability(ItemStack itemStack, int amount) {
		if(itemStack.getTag() != null)
			itemStack.getTag().putInt(ModValue.FLY_WHEEL_DURABILITY_TAG, amount);
	}
}
