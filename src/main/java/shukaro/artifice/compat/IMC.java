package shukaro.artifice.compat;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.util.NameMetaPair;

import java.util.ArrayList;

public class IMC
{
    public static void processIMC(IMCEvent event)
    {
        for (IMCMessage m : event.getMessages())
        {
            if (m.key.equals("register-marble"))
                processMarbleIMC(event, m);
            else if (m.key.equals("register-basalt"))
                processBasaltIMC(event, m);
            else if (m.key.equals("register-sledge"))
                processSledgeIMC(event, m);
            else if (m.key.equals("register-dim-blacklist"))
                processDimBlacklistIMC(event, m);
            else if (m.key.equals("register-stone"))
                processStoneIMC(event, m);
            else if (m.key.equals("register-worldtype-blacklist"))
                processWorldTypeIMC(event, m);
            else
                ArtificeCore.logger.warn(String.format("Received IMC message with unknown key('%s') from %s!", m.key, m.getSender()));
        }
    }

    private static void processMarbleIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isStringMessage())
            {
                Splitter splitter = Splitter.on("@").trimResults();
                String[] array = Iterables.toArray(splitter.split(m.getStringValue()), String.class);
                if (array.length != 2)
                    ArtificeCore.logger.info(String.format("Received an invalid marble registration request %s from mod %s", m.getStringValue(), m.getSender()));
                else
                {
                    String name = array[0];
                    Integer meta = Ints.tryParse(array[1]);
                    if (Strings.isNullOrEmpty(name) || meta == null)
                        ArtificeCore.logger.info(String.format("Received an invalid marble registration request %s from mod %s", m.getStringValue(), m.getSender()));
                    else
                    {
                        Block block = (Block) Block.blockRegistry.getObject(name);
                        ArtificeRegistry.registerMarbleType(block, meta);
                    }
                }
            }
            else if (m.isItemStackMessage())
            {
                ItemStack stack = m.getItemStackValue();
                Block block = Block.getBlockFromItem(stack.getItem());
                if (block == null)
                    ArtificeCore.logger.info(String.format("Received an invalid marble registration request %s from mod %s", m.getItemStackValue(), m.getSender()));
                else
                    ArtificeRegistry.registerMarbleType(block, stack.getItemDamage());
            }
        }
        catch (Exception ex) {}
    }

    private static void processBasaltIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isStringMessage())
            {
                Splitter splitter = Splitter.on("@").trimResults();
                String[] array = Iterables.toArray(splitter.split(m.getStringValue()), String.class);
                if (array.length != 2)
                    ArtificeCore.logger.info(String.format("Received an invalid basalt registration request %s from mod %s", m.getStringValue(), m.getSender()));
                else
                {
                    String name = array[0];
                    Integer meta = Ints.tryParse(array[1]);
                    if (Strings.isNullOrEmpty(name) || meta == null)
                        ArtificeCore.logger.info(String.format("Received an invalid basalt registration request %s from mod %s", m.getStringValue(), m.getSender()));
                    else
                    {
                        Block block = (Block) Block.blockRegistry.getObject(name);
                        ArtificeRegistry.registerBasaltType(block, meta);
                    }
                }
            }
            else if (m.isItemStackMessage())
            {
                Block block = Block.getBlockFromItem(m.getItemStackValue().getItem());
                if (block == null)
                    ArtificeCore.logger.info(String.format("Received an invalid basalt registration request %s from mod %s", m.getItemStackValue(), m.getSender()));
                else
                    ArtificeRegistry.registerBasaltType(block, m.getItemStackValue().getItemDamage());
            }
        }
        catch (Exception ex) {}
    }

    private static void processSledgeIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isNBTMessage())
            {
                NBTTagCompound tag = m.getNBTValue();
                if (tag.getBoolean("wild"))
                {
                    Block block = Block.getBlockFromName(tag.getString("block"));
                    NBTTagList list = tag.getTagList("drops", Constants.NBT.TAG_COMPOUND);
                    ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
                    for (int i=0; i<list.tagCount(); i++)
                    {
                        NBTTagCompound drop = list.getCompoundTagAt(i);
                        NameMetaPair pair = new NameMetaPair(drop.getString("name"), drop.getInteger("meta"));
                        if (pair.isValidItem())
                            drops.add(new ItemStack(pair.getItem(), drop.getInteger("amount"), pair.getMetadata()));
                        else if (pair.isValidBlock())
                            drops.add(new ItemStack(pair.getBlock(), drop.getInteger("amount"), pair.getMetadata()));
                    }
                    ArtificeRegistry.registerWildSledgeBlock(block, drops);
                }
                else
                {
                    Block block = Block.getBlockFromName(tag.getString("block"));
                    int meta = tag.getInteger("meta");
                    NBTTagList list = tag.getTagList("drops", Constants.NBT.TAG_COMPOUND);
                    ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
                    for (int i=0; i<list.tagCount(); i++)
                    {
                        NBTTagCompound drop = list.getCompoundTagAt(i);
                        NameMetaPair pair = new NameMetaPair(drop.getString("name"), drop.getInteger("meta"));
                        if (pair.isValidItem())
                            drops.add(new ItemStack(pair.getItem(), drop.getInteger("amount"), pair.getMetadata()));
                        else if (pair.isValidBlock())
                            drops.add(new ItemStack(pair.getBlock(), drop.getInteger("amount"), pair.getMetadata()));
                    }
                    ArtificeRegistry.registerSledgeBlock(block, meta, drops);
                }
            }
        }
        catch (Exception ex) {}
    }

    private static void processDimBlacklistIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isStringMessage())
            {
                Integer id = Ints.tryParse(m.getStringValue());
                if (id == null)
                    ArtificeCore.logger.info(String.format("Received an invalid dimension blacklist registration request %s from mod %s", m.getStringValue(), m.getSender()));
                else
                    ArtificeRegistry.registerDimensionBlacklist(id);
            }
        }
        catch (Exception ex) {}
    }

    private static void processStoneIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isStringMessage())
            {
                Splitter splitter = Splitter.on("@").trimResults();
                String[] array = Iterables.toArray(splitter.split(m.getStringValue()), String.class);
                if (array.length != 2)
                    ArtificeCore.logger.info(String.format("Received an invalid stone registration request %s from mod %s", m.getStringValue(), m.getSender()));
                else
                {
                    String name = array[0];
                    Integer meta = Ints.tryParse(array[1]);
                    if (Strings.isNullOrEmpty(name) || meta == null)
                        ArtificeCore.logger.info(String.format("Received an invalid stone registration request %s from mod %s", m.getStringValue(), m.getSender()));
                    else
                    {
                        Block block = (Block) Block.blockRegistry.getObject(name);
                        ArtificeRegistry.registerStoneType(block, meta);
                    }
                }
            }
            else if (m.isItemStackMessage())
            {
                Block block = Block.getBlockFromItem(m.getItemStackValue().getItem());
                if (block == null)
                    ArtificeCore.logger.info(String.format("Received an invalid stone registration request %s from mod %s", m.getItemStackValue(), m.getSender()));
                else
                    ArtificeRegistry.registerStoneType(block, m.getItemStackValue().getItemDamage());
            }
        }
        catch (Exception ex) {}
    }

    private static void processWorldTypeIMC(IMCEvent event, IMCMessage m)
    {
        try
        {
            if (m.isStringMessage())
                ArtificeRegistry.registerWorldTypeBlacklist(m.getStringValue());
        }
        catch (Exception ex) {}
    }
}
