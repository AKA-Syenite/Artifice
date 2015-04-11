package shukaro.artifice.compat;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;

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
                    Block block = (Block) Block.blockRegistry.getObject(name);
                    if (Strings.isNullOrEmpty(name) || meta == null || block == null)
                        ArtificeCore.logger.info(String.format("Received an invalid marble registration request %s from mod %s", m.getStringValue(), m.getSender()));
                    else
                        ArtificeRegistry.registerMarbleType(new ItemStack(block, 1, meta));
                }
            }
            else if (m.isItemStackMessage())
            {
                ItemStack stack = m.getItemStackValue();
                Block block = Block.getBlockFromItem(stack.getItem());
                if (block == null)
                    ArtificeCore.logger.info(String.format("Received an invalid marble registration request %s from mod %s", m.getItemStackValue(), m.getSender()));
                else
                    ArtificeRegistry.registerMarbleType(stack);
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
                    Block block = (Block) Block.blockRegistry.getObject(name);
                    if (Strings.isNullOrEmpty(name) || meta == null || block == null)
                        ArtificeCore.logger.info(String.format("Received an invalid basalt registration request %s from mod %s", m.getStringValue(), m.getSender()));
                    else
                        ArtificeRegistry.registerBasaltType(new ItemStack(block, 1, meta));
                }
            }
            else if (m.isItemStackMessage())
            {
                ItemStack stack = m.getItemStackValue();
                Block block = Block.getBlockFromItem(stack.getItem());
                if (block == null)
                    ArtificeCore.logger.info(String.format("Received an invalid basalt registration request %s from mod %s", m.getItemStackValue(), m.getSender()));
                else
                    ArtificeRegistry.registerBasaltType(stack);
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
