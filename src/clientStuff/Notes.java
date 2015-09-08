/*
 * 
 * Events don't work |:-(
 * 
 * in second launcher, only 1st 2 cards get clicked
 * 
 * CardDragger is started but doesn't do anything.
 * 
 * CardDragger runs fine, but card is not dragged
 * 
 * 
 * Playing Creatures kind of works, but mouse click coordinates are very wrong
 * 
 *    never mind, coordinates are wrong but playing cards is badly broken too, cards only show up in 1st lane
 *    
 *    card playing works better now, but coords are still off
 *    
 *    coords are only wrong sometimes, the first two cards seem to work
 *    
 *    I know why the coordinates are wrong, it does not update them after you play cards, but I haven't been
 *    able to fix it yet
 *    
 *    now, after you drag another card, the coordinates fix themselves. I wonder why they don't do it at the
 *    right time
 *
 *    July 25 
 *    
 *    click coordinates should be fixed now
 *	  
 *	
 *
 *	  Reading from the playerdata file is broken, trying to fix it.
 *
 *		August 6
 *
 *		reading from file works now
 *
 *
 *	Worked on getting creatures to attack, not quite finished with it yet
 *
 *
 *		August 27
 *
 *	Added the coming into play delay, but the creature never appears
 *	
 *	Sept. 3
 *	
 *	Fixed playing creatures, working more on attacking/blocking
 *
 *
 *	Having a creature with a double-digit ID breaks the system
 *	Not anymore I think
 *
 *	Game does not register the block button being pressed
 *    
 * */ 