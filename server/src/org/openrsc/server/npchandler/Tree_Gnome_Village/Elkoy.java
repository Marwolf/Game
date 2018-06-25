/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/

//scripted by Mr. Zain

package org.openrsc.server.npchandler.Tree_Gnome_Village;
import org.openrsc.server.Config;
import org.openrsc.server.event.DelayedQuestChat;
import org.openrsc.server.event.SingleEvent;
import org.openrsc.server.model.ChatMessage;
import org.openrsc.server.model.MenuHandler;
import org.openrsc.server.model.Npc;
import org.openrsc.server.model.Player;
import org.openrsc.server.model.Quest;
import org.openrsc.server.model.World;
import org.openrsc.server.npchandler.NpcHandler;



public class Elkoy implements NpcHandler {

	public void handleNpc(final Npc npc, final Player owner) throws Exception {
		npc.blockedBy(owner);
		owner.setBusy(true);
		Quest q = owner.getQuest(Config.Quests.TREE_GNOME_VILLAGE);
		if(q != null) {
			if(q.finished()) {
				finished(npc, owner);
			} else {
				switch(q.getStage()) {
					case 0:
						noQuestStarted(npc, owner);
						break;
					case 1:
						questStage1(npc, owner);
						break;
					case 2:
						questStage1(npc, owner);
						break;
					case 3:
						questStage1(npc, owner);
						break;
					case 4:
						questStage1(npc, owner);
						break;
					case 5:
						questStage5(npc, owner);
						break;
					case 6:
						questStage6(npc, owner);
						break;
				}
			}
		} else {
			noQuestStarted(npc, owner);
		}
	}
	

	private void noQuestStarted(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello there"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Hello, welcome to our maze", "I'm Elkoy the tree gnome"}) {
					public void finished() {
						World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"I haven't heard of your sort"}) {
							public void finished() {
								World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"There's not many of us left", "once you could find tree gnomes anywhere in the world", "Now we hide in small groups to avoid capture" }) {
									public void finished() {
										World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Capture By whom?"}) {
											public void finished() {
												World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Tree gnomes have been hunted for so called 'fun'", "Since I can remember, our main threat nowadays", "Are General Khazard's troops, they know no mercy", "But are also very dense", "They'll never find their way through our maze", "Have fun!"}) {
													public void finished() {
														owner.setBusy(false);
														npc.unblock();
													}
												});
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});
	}
	
	private void questStage1(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello Elkoy"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Welcome back!", "Would you like me to show you the way to the village?"}) {
					public void finished() {
						World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
							public void action() {
								final String[] options107 = {"Yes please", "No thanks Elkoy"};
								owner.setBusy(false);
								owner.sendMenu(options107);
								owner.setMenuHandler(new MenuHandler(options107) {
									public void handleReply(final int option, final String reply) {
										owner.setBusy(true);
										for(Player informee : owner.getViewArea().getPlayersInView()) {
											informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
										}
										switch(option) {
											case 0:
												followMe(npc, owner);
												break;
											case 1:
												owner.setBusy(false);
												npc.unblock();
												break;
										}
									}
								});
							}
						});
					}
				});
			}
		});
	}
	
	private void questStage5(final Npc npc, final Player owner) {
		if(owner.getInventory().countId(740) < 1) {
			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello Elkoy"}, true) {
				public void finished() {
					World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're back! and the orb?"}) {
						public void finished() {
							World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"I haven't obtained it yet"}) {
								public void finished() {
									World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You should probably go retrieve it then", "I'll be waiting here"}) {
										public void finished() {
										owner.setBusy(false);
										npc.unblock();
										}
									});
								}
							});	
						}
					});
				}
			});	
		}
		else
		if(owner.getInventory().countId(740) > 0) {
			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello Elkoy"}, true) {
				public void finished() {
					World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're back! and the orb?"}) {
						public void finished() {
							World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"I have it here"}) {
								public void finished() {
									World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're our saviour", "Please return it to the village and we are all saved", "Would you like me to show you the way to the village?"}) {
										public void finished() {
											World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
												public void action() {
													final String[] options107 = {"Yes please", "No thanks Elkoy"};
													owner.setBusy(false);
													owner.sendMenu(options107);
													owner.setMenuHandler(new MenuHandler(options107) {
														public void handleReply(final int option, final String reply) {
															owner.setBusy(true);
															for(Player informee : owner.getViewArea().getPlayersInView()) {
																informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
															}
															switch(option) {
																case 0:
																	followMe(npc, owner);
																	break;
																case 1:
																	owner.setBusy(false);
																	npc.unblock();
																	break;
															}
														}
													});
												}
											});
										}
									});
								}
							});
						}
					});
				}
			});
		}
	}
	
	private void questStage6(final Npc npc, final Player owner) {
		if(owner.getInventory().countId(741) < 1) {
			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello Elkoy"}, true) {
				public void finished() {
					World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're back! and the orbs?"}) {
						public void finished() {
							World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"I haven't obtained them yet"}) {
								public void finished() {
									World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You should probably go retrieve them then", "I'll be waiting here"}) {
										public void finished() {
										owner.setBusy(false);
										npc.unblock();
										}
									});
								}
							});	
						}
					});
				}
			});	
		}
		else
		if(owner.getInventory().countId(741) > 0) {
			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello Elkoy"}, true) {
				public void finished() {
					World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You truly are a hero"}) {
						public void finished() {
							World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Thanks"}) {
								public void finished() {
									World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You saved us by returning the orbs of protection ", "I'm humbled and wish you well", "Would you like me to show you the way to the village?"}) {
										public void finished() {
											World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
												public void action() {
													final String[] options107 = {"Yes please", "No thanks Elkoy"};
													owner.setBusy(false);
													owner.sendMenu(options107);
													owner.setMenuHandler(new MenuHandler(options107) {
														public void handleReply(final int option, final String reply) {
															owner.setBusy(true);
															for(Player informee : owner.getViewArea().getPlayersInView()) {
																informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
															}
															switch(option) {
																case 0:
																	followMe(npc, owner);
																	break;
																case 1:
																	owner.setBusy(false);
																	npc.unblock();
																	break;
															}
														}
													});
												}
											});
										}
									});
								}
							});
						}
					});
				}
			});
		}
	}
	
	private void followMe(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ok then, follow me!"}) {
			public void finished() {
			owner.teleport(644, 697, false);
			owner.sendMessage("Elkoy leads you to the gnome village");
			owner.setBusy(false);
			npc.unblock();
			}
		});
	}
	
	private void finished(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Hello again", "thank you for your help"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"No problem"}) {
					public void finished() {
						World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Would you like me to show you the way to the village?"}) {
							public void finished() {
								World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
									public void action() {
										final String[] options107 = {"Yes please", "No thanks Elkoy"};
										owner.setBusy(false);
										owner.sendMenu(options107);
										owner.setMenuHandler(new MenuHandler(options107) {
											public void handleReply(final int option, final String reply) {
											owner.setBusy(true);
												for(Player informee : owner.getViewArea().getPlayersInView()) {
													informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
												}
												switch(option) {
													case 0:
														followMe(npc, owner);
														break;
													case 1:
														owner.setBusy(false);
														npc.unblock();
														break;
												}
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});
	}
	
}