/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/
//npc ID 495
package org.openrsc.server.npchandler.Biohazard;
import org.openrsc.server.Config;
import org.openrsc.server.event.DelayedQuestChat;
import org.openrsc.server.event.FightEvent;
import org.openrsc.server.event.SingleEvent;
import org.openrsc.server.model.ChatMessage;
import org.openrsc.server.model.InvItem;
import org.openrsc.server.model.MenuHandler;
import org.openrsc.server.model.Npc;
import org.openrsc.server.model.Player;
import org.openrsc.server.model.Quest;
import org.openrsc.server.model.World;
import org.openrsc.server.npchandler.NpcHandler;



public class Sick_Mourner implements NpcHandler
 {
	public void handleNpc(final Npc npc, final Player owner) throws Exception
	{
		npc.blockedBy(owner);
		owner.setBusy(true);
		
		Quest q = owner.getQuest(Config.Quests.BIOHAZARD);
		Quest plagueCity = owner.getQuest(Config.Quests.PLAGUE_CITY);
		
		if(q != null) 
		{
			if(q.finished()) 
			{
				owner.sendMessage("The Mourner seems to be too busy to talk");
				owner.setBusy(false);
				npc.unblock();
			}
			else 
			{
				if(owner.getQuest(Config.Quests.BIOHAZARD) != null && owner.getQuest(Config.Quests.BIOHAZARD).getStage() == 4)
				{
					switch(q.getStage())
					{
						case 4:
							if (owner.getInventory().wielding(802))
							{
								questStage4(npc, owner);
							}
							else
							{
								World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're no doctor!"})
								{
									public void finished()
									{
										owner.setBusy(false);
										npc.unblock();
										FightEvent fe = new FightEvent(owner, npc, true);
										npc.setFightEvent(fe);
										owner.setFightEvent(fe);
										World.getDelayedEventHandler().add(fe);
									}
								});
							}
						break;
					}
				}
				else
				{
					owner.sendMessage("The Mourner seems to be too busy to talk");
					owner.setBusy(false);
					npc.unblock();
				}
			}
		} 
		else
		{
			owner.sendMessage("The Mourner seems to be too busy to talk");
			owner.setBusy(false);
			npc.unblock();
		}
	}
	
	
	private void questStage4(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello there"}, true)
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're here at last", "I don't know what I've eaten", "But I feel like I'm on death's door"})
				{
					public void finished()
					{
						World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hmm... Interesting, sounds like food poisoning"})
						{
							public void finished()
							{
								World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Yes, I'd figured that out already", "What can you give me to help?"})
								{
									public void finished()
									{
										World.getDelayedEventHandler().add(new SingleEvent(owner, 2000)
										{
											public void action()
											{
												final String[] options107 = {"Just hold your breath and count to ten", "The best I can do is pray for you", "There's nothing I can do, it's fatal"};
												owner.setBusy(false);
												owner.sendMenu(options107);
												owner.setMenuHandler(new MenuHandler(options107) 
												{
													public void handleReply(final int option, final String reply)
													{
														owner.setBusy(true);
														for(Player informee : owner.getViewArea().getPlayersInView())
														{
															informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
														}
														switch(option) 
														{
															case 0:
																World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"What, how will that help?", "What kind of doctor are you?"})
																{
																	public void finished()
																	{
																		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Erm.. I'm new, I just started"})
																		{
																			public void finished()
																			{
																				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're no doctor!"})
																				{
																					public void finished()
																					{
																						owner.setBusy(false);
																						npc.unblock();
																						FightEvent fe = new FightEvent(owner, npc, true);
																						npc.setFightEvent(fe);
																						owner.setFightEvent(fe);
																						World.getDelayedEventHandler().add(fe);
																					}
																				});
																			}
																		});
																	}
																});
															break;
															case 1:
																World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Pray for me?", "You're no doctor", "an imposter!"})
																{
																	public void finished()
																	{
																		owner.setBusy(false);
																		npc.unblock();
																		FightEvent fe = new FightEvent(owner, npc, true);
																		npc.setFightEvent(fe);
																		owner.setFightEvent(fe);
																		World.getDelayedEventHandler().add(fe);
																	}
																});
															break;
															case 2:
																World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"No, I'm too young to die", "I've never even had a girlfriend"})
																{
																	public void finished()
																	{
																		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"That's life for you"})
																		{
																			public void finished()
																			{
																				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Wait a minute, where's your equipment?"})
																				{
																					public void finished()
																					{
																						World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"it's..erm, at home"})
																						{
																							public void finished()
																							{
																								World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"You're no doctor!"})
																								{
																									public void finished()
																									{
																										owner.setBusy(false);
																										npc.unblock();
																										FightEvent fe = new FightEvent(owner, npc, true);
																										npc.setFightEvent(fe);
																										owner.setFightEvent(fe);
																										World.getDelayedEventHandler().add(fe);
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