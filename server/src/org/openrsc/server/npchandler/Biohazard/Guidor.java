/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/
//npc ID 508
package org.openrsc.server.npchandler.Biohazard;
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



public class Guidor implements NpcHandler
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
				questStage9(npc, owner);
			}
			else 
			{
				if(owner.getQuest(Config.Quests.BIOHAZARD) != null && owner.getQuest(Config.Quests.BIOHAZARD).getStage() >= 7)
				{
					switch(q.getStage())
					{
						case 7:
							questStage7(npc, owner);
						break;
						case 8:
							questStage8(npc, owner);
						break;
						case 9:
							questStage9(npc, owner);
						break;
					}
				}
				else
				{
					owner.sendMessage("Guidor seems to be too busy to talk");
					owner.setBusy(false);
					npc.unblock();
				}
			}
		} 
		else
		{
			owner.sendMessage("Guidor seems to be too busy to talk");
			owner.setBusy(false);
			npc.unblock();
		}
	}
	
	private void questStage7(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello you must be Guidor, I understand that you are unwell"}, true)
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Is my wife asking priests to visit me now?", "I'm a man of science for god's sake!", "Ever since she heard rumours of a plague carrier travelling from Ardougne", "She's kept me under house arrest", "Of course she means well, and I am quite frail now...", "So what brings you here?"})
				{
					public void finished()
					{
						World.getDelayedEventHandler().add(new SingleEvent(owner, 2000)
						{
							public void action()
							{
								final String[] options107 = {"I've come to ask your assistance in stopping a plague that could kill thousands", "Oh nothing, I was just going to bless your room and I've done that now. Goodbye"};
								owner.setBusy(false);
								owner.sendMenu(options107);
								owner.setMenuHandler(new MenuHandler(options107) 
								{
									public void handleReply(final int option, final String reply)
									{
										owner.setBusy(true);
										switch(option) 
										{
											case 0:
												plagueCarrier(npc, owner);
											break;
											case 1:
												World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Oh nothing, I was just going to bless your room and I've done that now. Goodbye"})
												{
													public void finished()
													{
														owner.setBusy(false);
														npc.unblock();
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
	
	private void plagueCarrier(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Well it's funny you should ask actually...", "I've come to ask your assistance in stopping a plague that could kill thousands"})
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"So you're the plague carrier!"})
				{
					public void finished()
					{
						World.getDelayedEventHandler().add(new SingleEvent(owner, 2000)
						{
							public void action()
							{
								final String[] options107 = {"No! Well, yes... But not exactly. It's contained in a sealed unit from Elena", "I've been sent by your old pupil Elena, she's trying to halt the virus"};
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
											case 1:
												World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Elena eh?"})
												{
													public void finished()
													{
														World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Yes, she wants you to analyse it", "You might be the only one that can help"})
														{
															public void finished()
															{
																World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Right then, sounds like we'd better get to work!"})
																{
																	public void finished()
																	{
																		if(owner.getInventory().countId(812) > 0)
																		{
																			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"I have the plague sample"})
																			{
																				public void finished()
																				{
																					if(owner.getInventory().contains(809) && owner.getInventory().contains(810) && owner.getInventory().contains(811) && owner.getInventory().contains(813)) //check for vials and touch paper
																					{
																						World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Now, I'll be needing some liquid honey, some sulphuric broline, and then..."})
																						{
																							public void finished()
																							{
																								World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"...Some ethenea and touch paper?"})
																								{
																									public void finished()
																									{
																										World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Indeed!"})
																										{
																											public void finished()
																											{
																												World.getDelayedEventHandler().add(new SingleEvent(owner, 1500)
																												{
																													public void action()
																													{
																														owner.sendMessage("You give him the vials, the sample and the touch paper");
																														owner.incQuestCompletionStage(Config.Quests.BIOHAZARD);
																														owner.getInventory().remove(809, 1);
																														owner.getInventory().remove(810, 1);
																														owner.getInventory().remove(811, 1);
																														owner.getInventory().remove(812, 1);
																														owner.getInventory().remove(813, 1);
																														owner.sendInventory();
																														World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Now I'll just apply these to the sample and...", "I don't get it...the touch paper has remained the same"})
																														{
																															public void finished()
																															{
																																World.getDelayedEventHandler().add(new SingleEvent(owner, 2000)
																																{
																																	public void action()
																																	{
																																		final String[] options107 = {"That's why Elena wanted you to do it, because she wasn't sure what was happening", "So what does that mean exactly?"};
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
																																					case 1:
																																						noPlague(npc, owner);
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
																						});
																					}
																					else
																					{
																						World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Now, I'll be needing some liquid honey, some sulphuric broline, some ethernea and touch paper"})
																						{
																							public void finished()
																							{
																								World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Looks like I don't have those on me", "I might need to get some more from Elena"})
																								{
																									public void finished()
																									{
																										owner.setBusy(false);
																										npc.unblock();
																									}
																								});																							
																							}
																						});
																					}
																				}
																			});
																		}
																		else
																		{
																			World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Looks like I don't have the sample on me", "I might need to get another from Elena"})
																			{
																				public void finished()
																				{
																					owner.setBusy(false);
																					npc.unblock();
																				}
																			});
																		}
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
	
	private void questStage8(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"I tested the sample you gave me and..", "I don't get it...the touch paper has remained the same"})
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new SingleEvent(owner, 2000)
				{
					public void action()
					{
						final String[] options107 = {"That's why Elena wanted you to do it, because she wasn't sure what was happening", "So what does that mean exactly?"};
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
									case 1:
										noPlague(npc, owner);
									break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void questStage9(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Hello again Guidor"}, true)
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Well hello traveller", "I still can't understand why they would lie about the plague"})
				{
					public void finished()
					{
						World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"It's strange, anyway how are you doing?"})
						{
							public void finished()
							{
								World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"I'm hanging in there"})
								{
									public void finished()
									{
										World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Good for you"})
										{
											public void finished()
											{
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
	
	private void noPlague(final Npc npc, final Player owner) 
	{
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Well that's just it. Nothing has happened", "I don't know what this sample is, but it certainly isn't toxic"})
		{
			public void finished()
			{
				World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"So what about the plague?"})
				{
					public void finished()
					{
						World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Don't you understand?", "There is no plague!", "I'm very sorry, I can see that you've worked hard for this...", "...but it seems that someone has been lying to you", "The only question is...", "...why?"})
						{
							public void finished()
							{
								owner.incQuestCompletionStage(Config.Quests.BIOHAZARD);
								owner.setBusy(false);
								npc.unblock();
							}
						});
					}
				});
			}
		});
	}
}