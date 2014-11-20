class TwentyOneCard extends Card
{
    /**
     * Constructor
     * rank can be 2, 3, ..., 10, JACK, QUEEN, KING, ACE
     * suit can be CLUBS, DIAMONDS, HEARTS, SPADES
     */
    public TwentyOneCard (int rank, Suit suit)
    {
        super(rank, suit)
    }

    def int getRank()
    {
        if (rank == ACE)
        {
            return 11
        }
        if (rank > 10)
        {
            return 10
        }
        return rank
    }
}
