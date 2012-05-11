package fr.noogotte.easygamemode.exception;

import fr.aumgn.bukkitutils.command.exception.CommandError;

public class NoTargetInServer extends CommandError {

	private static final long serialVersionUID = -3139364881212075182L;


	public NoTargetInServer() {
		super("Ce joueur n'est pas sur le serveur.");
	}

}
