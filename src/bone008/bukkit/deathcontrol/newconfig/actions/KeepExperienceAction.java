package bone008.bukkit.deathcontrol.newconfig.actions;

import java.util.List;

import bone008.bukkit.deathcontrol.newconfig.ActionAgent;
import bone008.bukkit.deathcontrol.newconfig.ActionDescriptor;
import bone008.bukkit.deathcontrol.newconfig.DeathContext;
import bone008.bukkit.deathcontrol.util.ParserUtil;

public class KeepExperienceAction extends ActionDescriptor {

	private double keepPct;
	private boolean dropLeftovers;

	public KeepExperienceAction(List<String> args) {
		keepPct = 0;

		if (args.size() > 0) {
			keepPct = ParserUtil.parsePercentage(args.get(0));
			if (keepPct == -1 || keepPct > 1.0)
				throw new RuntimeException("invalid percentage: " + args.get(0));
		}

		dropLeftovers = (args.size() > 1 && args.get(1).equalsIgnoreCase("drop-leftovers"));
	}

	@Override
	public ActionAgent createAgent(DeathContext context) {
		return new KeepExperienceActionAgent(context, keepPct, dropLeftovers);
	};
}
