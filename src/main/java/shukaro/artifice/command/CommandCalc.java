package shukaro.artifice.command;

import fathzer.javaluator.DoubleEvaluator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

import java.util.Arrays;
import java.util.List;

public class CommandCalc extends CommandBase
{
    private DoubleEvaluator eva;

    public CommandCalc()
    {
        eva = new DoubleEvaluator();
    }

    @Override
    public String getCommandName() { return "calc"; }

    @Override
    public List getCommandAliases()
    {
        return Arrays.asList("calculate");
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "commands.artifice.calc";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length == 0)
        {
            sender.addChatMessage(new ChatComponentTranslation("commands.artifice.calc.syntax"));
            return;
        }
        String exp = "";
        for (String s : args)
            exp += s;
        try
        {
            sender.addChatMessage(new ChatComponentText(exp + " = " + eva.evaluate(exp).toString()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            sender.addChatMessage(new ChatComponentTranslation("commands.artifice.calc.error"));
        }
    }
}
