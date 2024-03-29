package actions;

import gui.StartFrame;

public class ActionManager {

	private StartBrowseAction startBrowseAction;
	private StartCancelAction startCancelAction;
	private StartLaunchAction startLaunchAction;

	private NewEntityAction newEntityAction;
	private UpdateAction updateAction;
	private AddSubEntityAction addSubEntityAction;
	private SearchAction searchAction;
	private DeleteSingleAction deleteSingleAction;
	private DeleteMultipleAction deleteMultipleAction;
	private ConfigurationAction configurationAction;

	public ActionManager(StartFrame startFrame) {
		initialiseStartActions(startFrame);
	}

	public ActionManager() {
		initialiseStartActions();
	}

	private void initialiseStartActions() {
		newEntityAction = new NewEntityAction();
		addSubEntityAction = new AddSubEntityAction();
		searchAction = new SearchAction();
		deleteSingleAction = new DeleteSingleAction();
		deleteMultipleAction = new DeleteMultipleAction();
		configurationAction = new ConfigurationAction();
		updateAction = new UpdateAction();
	}

	private void initialiseStartActions(StartFrame startFrame) {
		startBrowseAction = new StartBrowseAction(startFrame);
		startCancelAction = new StartCancelAction(startFrame);
		startLaunchAction = new StartLaunchAction(startFrame);

	}

	public StartBrowseAction getStartBrowseAction() {
		return startBrowseAction;
	}

	public StartCancelAction getStartCancelAction() {
		return startCancelAction;
	}

	public StartLaunchAction getStartLaunchAction() {
		return startLaunchAction;
	}

	public UpdateAction getUpdateAction() {
		return updateAction;
	}

	public NewEntityAction getNewEntityAction() {
		return newEntityAction;
	}

	public AddSubEntityAction getAddSubEntityAction() {
		return addSubEntityAction;
	}

	public ConfigurationAction getConfigurationAction() {
		return configurationAction;
	}

	public DeleteMultipleAction getDeleteMultipleAction() {
		return deleteMultipleAction;
	}

	public DeleteSingleAction getDeleteSingleAction() {
		return deleteSingleAction;
	}

	public SearchAction getSearchAction() {
		return searchAction;
	}

}
