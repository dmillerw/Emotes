package vazkii.emotes.common;

import vazkii.emotes.client.emote.base.EmoteHandler;
import vazkii.emotes.common.network.PacketEmote;
import vazkii.emotes.common.network.PacketHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;
import java.util.List;

public class CommandEmote extends CommandBase {

	@Override
	public String getCommandName() {
		return "emote";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "<emote>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length > 0 && sender instanceof EntityPlayer)
			PacketHandler.INSTANCE.sendToAll(new PacketEmote(args[0], sender.getCommandSenderName()));
	}

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        List<String> list = new ArrayList<String>();
        String arg = args[0];
        if ("list".startsWith(arg)) list.add("list");
        if ("stop".startsWith(arg)) list.add("stop");
        for (String emote : EmoteHandler.emoteMap.keySet()) {
            if (emote.startsWith(arg)) list.add(emote);
        }
        return list;
    }

}
