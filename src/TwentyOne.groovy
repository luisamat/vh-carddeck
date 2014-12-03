// 52 Cards 
// Dealer and Player
// Rule: Dealer has to always hit on 14
// Bust = Over Twenty One
// ACE = 1 or 11
// All face cards = 10
// Dealer deals one up one down for himself
// Dealer deals two cards for each Player
// Each player can Hit or Stand 
// All players go and then the Dealer
// Play until the player wants to stand or is busted
// Compare Dealer hand with player hand

import Deck
import Card

def playerHand = [] 
def dealerShowing = [] 
def dealerHidden // one Card

def deck = new Deck (true) // true means we're playing 21
deck.shuffle()

def standing = false

playerHand.addAll(deck.deal(2)) // deal two initial cards
dealerHidden = deck.deal() // this is the face down card
dealerShowing.add(deck.deal()) // this is the face up card

def playerHandValue = handValue(playerHand)
def dealerShowingValue = handValue(dealerShowing)

while(!standing && playerHandValue < 21 || dealerShowingValue < 14) // "!" means "not"
{ 
    // Players(s) "go" -- we only have one
    // If we had more than one player, we would have another loop here.
    println ""
    println "Player has " + playerHandValue + " " + playerHand
    println "Dealer has " + dealerShowingValue + " " + dealerShowing
    println ""

    if (!standing && playerHandValue <  21)
    {
	    def move = hitOrStand()
		//println "Thanks... you entered: " + move
		if (move == "s")
		{
			standing = true
		}
		else
		{
			playerHand.add(deck.deal())
	        playerHandValue = handValue(playerHand)
		}
	}

    if (dealerShowingValue <= 14)
    {
    	dealerShowing.add(deck.deal())
    	dealerShowingValue = handValue(dealerShowing)
    }
}

// Determine the winner
dealerShowing.add(dealerHidden)
dealerShowingValue = handValue(dealerShowing)
println ""
println "FINAL HANDS: "
println "    PLayer: " + playerHandValue + " " + playerHand
println "    Dealer: " + dealerShowingValue + " " + dealerShowing
println ""
if (playerHandValue > dealerShowingValue)
{
	if (playerHandValue > 21)
	{
		println "House WINS"
	}
	else
	{
	    println "Player WINS!"
	}
}
else
if (playerHandValue < dealerShowingValue)
{
	if (dealerShowingValue > 21)
	{
		println "Player WINS!"
	}
	else
	{
	    println "House WINS"
	}
}
else
{
	println "DRAW"
}
println ""

static def hitOrStand()
{
	// Take user input until the user enters something valid ("h" or "s")
	def text = ''
	while(text != 'h' && text != 's')
	{
	   print "Hit or Stand [h | s]: "
	   text = System.console().readLine()
	}
	return text
}

static def handValue(hand)
{
	// Calculate the hand value (using low aces)
	def value = 0
	def lowAces = []
	for (Card c : hand)
	{
	   def rank = c.rank
	   value += rank
	   if (rank == 1)
	   {
           lowAces.add(c)
	   }
	}

	// Calculate taking high aces into account
	while (value <= 11 && lowAces.size()>0)
	{
		value -= 1  // Take away a low ace
		value += 11 // Add a high ace
		lowAces.remove(lowAces[0]) // remove an ace from the list of low aces from the hand
	}
	return value
}